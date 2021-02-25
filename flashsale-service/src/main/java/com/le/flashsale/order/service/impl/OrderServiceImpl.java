
package com.le.flashsale.order.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.le.flashsale.activity.service.ActivityInfoService;
import com.le.flashsale.adapter.CacheAdapter;
import com.le.flashsale.adapter.DistributeLockAdapter;
import com.le.flashsale.common.Constant;
import com.le.flashsale.common.enums.OrderEnums;
import com.le.flashsale.common.gson.GsonUtils;
import com.le.flashsale.common.id.SnowFlake;
import com.le.flashsale.order.dao.OrderDAO;
import com.le.flashsale.order.dto.AddOrderDTO;
import com.le.flashsale.order.dto.AddOrderResultDTO;
import com.le.flashsale.order.dto.NotifyDTO;
import com.le.flashsale.order.dto.OrderDTO;
import com.le.flashsale.order.dto.OrderDetailDTO;
import com.le.flashsale.order.dto.ProcessOrderDto;
import com.le.flashsale.order.service.OrderService;

/**
 * Date 2020/11/16 6:46 下午
 * Author le
 */
@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private SnowFlake snowFlake = new SnowFlake(Constant.DATA_CENTER_ID, Constant.MACHINE_ID);

    @Resource
    private DistributeLockAdapter distributeLockAdapter;

    @Resource
    private CacheAdapter cacheAdapter;

    @Resource
    private ActivityInfoService activityInfoService;

    @Resource
    private OrderDAO orderDAO;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private Environment environment;

    /**
     * 下单操作
     *
     * @param addOrderDTO
     * @return
     */
    @Override
    public AddOrderResultDTO addOrder(AddOrderDTO addOrderDTO) {
        AddOrderResultDTO addOrderResultDto = new AddOrderResultDTO();

        // 控制维度应该是：活动+用户
        String lockKey = Constant.REDIS_PREFIX_ADD_ORDER + addOrderDTO.getActivityId() + "#" + addOrderDTO.getUserId();
        /**
         *【第一步】限流处理——减少同一用户对某一个商品的大量重复请求
         * 1> 查询redis中是否已经抢过该产品，如果已经被抢过，则返回空的订单号
         * 2> 并将产品ID与用户ID缓存到Redis中，并设置合理的过期时间（比如：活动持续时间）
         */
        if (!distributeLockAdapter.lock(lockKey)) {
            addOrderResultDto.setMessage(Constant.MESSAGE_ORDER_FAILED_1);
            return addOrderResultDto;
        }

        /**
         *【第二步】查看缓存中的产品是否还有库存
         * 如果有，则缓存中减一
         * 如果没有，则直接返回
         */
        String stockKey = Constant.REDIS_PREFIX_ACTIVITY_STOCK_NUMS + addOrderDTO.getActivityId();
        if (StringUtils.isBlank(cacheAdapter.get(stockKey))) {
            addOrderResultDto.setMessage(Constant.MESSAGE_ORDER_FAILED_3);
            return addOrderResultDto;
        }

        // 入库扣减库存失败，则说明没有库存了
        if (!Constant.SUCCESS.equals(cacheAdapter.execute(Arrays.asList(stockKey)))) {
            distributeLockAdapter.unlock(lockKey); // 如果无库存，解锁已经addOrder的key。提示商品已售空。
            addOrderResultDto.setMessage(Constant.MESSAGE_ORDER_FAILED_2);
            return addOrderResultDto;
        }

        // 真实的活动库存减1
        activityInfoService.deduceProductNums(addOrderDTO.getActivityId());

        /**
         * 将订单任务发送到MQ
         */
        ProcessOrderDto processOrderDto = new ProcessOrderDto();
        processOrderDto.setOrderNo(String.valueOf(snowFlake.nextId())); // 利用雪花算法，生成订单号
        processOrderDto.setUserId(addOrderDTO.getUserId());
        processOrderDto.setProductId(addOrderDTO.getProductId());
        processOrderDto.setActivityId(addOrderDTO.getActivityId());
        addOrderResultDto.setMessage(Constant.MESSAGE_ORDER_SUBMITED);

        // 异步发送消息触发订单处理逻辑
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(environment.getProperty("mq.flashsale.process.order.exchange"));
        rabbitTemplate.setRoutingKey(environment.getProperty("mq.flashsale.process.order.routing.key"));

        //TODO：将info充当消息发送至队列
        rabbitTemplate.convertAndSend(processOrderDto,
                message -> {
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                            NotifyDTO.class);
                    return message;
                });

        return addOrderResultDto;
    }

    /**
     * 处理订单操作
     *
     * @param processOrderDto
     */
    @RabbitListener(queues = {"${mq.flashsale.process.order.queue}"}, containerFactory = "singleListenerContainer")
    @Override
    public void processOrder(ProcessOrderDto processOrderDto) {
        // 添加订单到订单表中
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNo(processOrderDto.getOrderNo());
        orderDTO.setProductId(processOrderDto.getProductId());
        orderDTO.setStatus(OrderEnums.WAIT_PAY.getCode());
        orderDTO.setUserId(processOrderDto.getUserId());
        orderDTO.setActivityId(processOrderDto.getActivityId());
        orderDAO.insert(orderDTO);

        /**
         * TODO 大家切记！真实的业务场景，会有更多其他业务的操作。此处省略。
         */

        // 发送抢购成功短信
        // sendSuccessMessage(orderDTO.getId());

        // 处理超时未支付订单
        // nonPayOrderHandler(orderDTO.getId());
    }

    @Override
    public OrderDetailDTO queryOrderInfoByOrderNo(String orderNo) {
        return orderDAO.selectByOrderNo(orderNo);
    }

    private void sendSuccessMessage(Long orderId) {
        // 添加成功后，异步发送邮件通知
        logger.info("开始发送订单的通知消息：orderId={}", orderId);

        try {
            if (orderId != null) {
                OrderDTO orderDTO = orderDAO.selectByPrimaryKey(orderId);
                NotifyDTO notifyDTO = new NotifyDTO();
                notifyDTO.setOrderNo(orderDTO.getOrderNo());
                notifyDTO.setUserId(orderDTO.getUserId());
                notifyDTO.setOrderId(orderDTO.getId());
                if (orderDTO != null) {
                    // TODO:rabbitmq发送消息的逻辑
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(environment.getProperty("mq.flashsale.send.email.exchange"));
                    rabbitTemplate.setRoutingKey(environment.getProperty("mq.flashsale.send.email.routing.key"));

                    // TODO：将info充当消息发送至队列
                    rabbitTemplate.convertAndSend(notifyDTO,
                            message -> {
                                MessageProperties messageProperties = message.getMessageProperties();
                                messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                                messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                                        NotifyDTO.class);
                                return message;
                            });
                }
            }
        } catch (Exception e) {
            logger.error("发送订单的通知消息异常！orderId={}", orderId, e);
        }
    }

    /**
     * 秒杀成功后生成抢购订单-发送信息入死信队列，等待着一定时间失效超时未支付的订单
     *
     * @param orderId
     */
    private void nonPayOrderHandler(Long orderId) {
        try {
            if (orderId != null) {
                OrderDTO orderDTO = orderDAO.selectByPrimaryKey(orderId);
                if (orderDTO != null) {
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(environment.getProperty("mq.flashsale.not.pay.handler.exchange"));
                    rabbitTemplate.setRoutingKey(environment.getProperty("mq.flashsale.not.pay.handler.routing.key"));
                    rabbitTemplate.convertAndSend(orderDTO, message -> {
                        MessageProperties mp = message.getMessageProperties();
                        mp.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        mp.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, OrderDTO.class);
                        mp.setExpiration(environment.getProperty("mq.flashsale.not.pay.handler.ttl"));
                        return message;
                    });
                }
            }
        } catch (Exception e) {
            logger.error("将订单添加到死信队列失败！", e);
        }
    }

    /**
     * 订单超时未支付处理逻辑
     *
     * @param orderDTO
     */
    @RabbitListener(queues = {"${mq.flashsale.not.pay.handler.queue}"}, containerFactory = "singleListenerContainer")
    private void nonPayOrderHandler(OrderDTO orderDTO) {
        try {
            logger.info("订单超时未支付处理逻辑开始处理。orderDTO={}", GsonUtils.toJson(orderDTO));

            if (orderDTO != null) {
                orderDTO = orderDAO.selectByPrimaryKey(orderDTO.getId());
                // 订单存在，并且订单状态是待支付
                if (orderDTO != null && OrderEnums.WAIT_PAY.getCode().equals(orderDTO.getStatus())) {
                    // 将订单状态修改为取消状态。
                    orderDTO.setStatus(OrderEnums.CANCEL.getCode());
                    orderDAO.updateByPrimaryKeySelective(orderDTO);

                    // 将活动库存和缓存分别加1
                    activityInfoService.increaseProductNums(orderDTO.getActivityId());
                }
            }
        } catch (Exception e) {
            logger.error("订单超时未支付处理逻辑处理异常：", e.fillInStackTrace());
        }
    }

}
