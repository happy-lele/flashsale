
package com.le.flashsale.config;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.google.common.collect.Maps;

@Configuration
public class RabbitmqConfig {

    private final static Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Resource
    private Environment environment;

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Resource
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 单一消费者
     *
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        return factory;
    }

    /**
     * 多个消费者
     *
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //确认消费模式-NONE
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(
                environment.getProperty("spring.rabbitmq.listener.simple.concurrency", int.class));
        factory.setMaxConcurrentConsumers(
                environment.getProperty("spring.rabbitmq.listener.simple.max-concurrency", int.class));
        factory.setPrefetchCount(environment.getProperty("spring.rabbitmq.listener.simple.prefetch", int.class));
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(
                (correlationData, ack, cause) -> log
                        .info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log
//                .warn("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey,
//                        replyCode, replyText, message));
        return rabbitTemplate;
    }

    /**
     * 成功处理下单消息模型
     *
     * @return
     */
    @Bean
    public Queue addOrderSuccessQueue() {
        return new Queue(environment.getProperty("mq.flashsale.process.order.queue"), true);
    }

    @Bean
    public TopicExchange addOrderSuccessExchange() {
        return new TopicExchange(environment.getProperty("mq.flashsale.process.order.exchange"), true, false);
    }

    @Bean
    public Binding addOrderSuccessBinding() {
        return BindingBuilder.bind(addOrderSuccessQueue()).to(addOrderSuccessExchange())
                .with(environment.getProperty("mq.flashsale.process.order.routing.key"));
    }

    /**
     * 订单处理成功后，发送消息
     *
     * @return
     */
    @Bean
    public Queue successEmailQueue() {
        return new Queue(environment.getProperty("mq.flashsale.send.email.queue"), true);
    }

    @Bean
    public TopicExchange successEmailExchange() {
        return new TopicExchange(environment.getProperty("mq.flashsale.send.email.exchange"), true, false);
    }

    @Bean
    public Binding successEmailBinding() {
        return BindingBuilder.bind(successEmailQueue()).to(successEmailExchange())
                .with(environment.getProperty("mq.flashsale.send.email.routing.key"));
    }


    /**
     * 构建秒杀成功之后-订单超时未支付的死信队列消息模型
     *
     * 第一步：声明Exchange
     */
    @Bean // 创建业务Exchange
    public TopicExchange noPayHandlerExchange(){
        return new TopicExchange(environment.getProperty("mq.flashsale.not.pay.handler.exchange"),true,false);
    }
    @Bean // 创建死信Exchange
    public TopicExchange noPayDeadLetterExchange(){
        return new TopicExchange(environment.getProperty("mq.flashsale.not.pay.dead.letter.exchange"),true,false);
    }

    /**
     * 第二步：声明Queue
     */
    @Bean // 创建死信队列（添加【死信交换机】和【死信路由key】）
    public Queue noPayDeadLetterQueue(){
        Map<String, Object> argsMap= Maps.newHashMap();
        argsMap.put("x-dead-letter-exchange",environment.getProperty("mq.flashsale.not.pay.dead.letter.exchange"));
        argsMap.put("x-dead-letter-routing-key",environment.getProperty("mq.flashsale.not.pay.dead.letter.routing.key"));
        return new Queue(environment.getProperty("mq.flashsale.not.pay.dead.letter.queue"),true,false,false,argsMap);
    }
    @Bean // 创建业务队列
    public Queue noPayHandlerQueue(){
        return new Queue(environment.getProperty("mq.flashsale.not.pay.handler.queue"),true);
    }

    /**
     * 第三步：构建QUEUE与Exchange的绑定关系
     */
    @Bean // 创建绑定关系
    public Binding noPayDeadLetterBinding(){
        return BindingBuilder.bind(noPayDeadLetterQueue()).to(noPayHandlerExchange()).with(
                environment.getProperty("mq.flashsale.not.pay.handler.routing.key"));
    }
    @Bean // 创建绑定关系
    public Binding noPayHandlerBinding(){
        return BindingBuilder.bind(noPayHandlerQueue()).to(noPayDeadLetterExchange()).with(
                environment.getProperty("mq.flashsale.not.pay.dead.letter.routing.key"));
    }

}