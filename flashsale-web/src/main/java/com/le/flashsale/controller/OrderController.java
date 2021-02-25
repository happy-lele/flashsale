/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.le.flashsale.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.le.flashsale.activity.service.ActivityInfoService;
import com.le.flashsale.common.Constant;
import com.le.flashsale.common.enums.OrderEnums;
import com.le.flashsale.dto.BaseResponse;
import com.le.flashsale.order.dto.AddOrderDTO;
import com.le.flashsale.order.dto.AddOrderResultDTO;
import com.le.flashsale.order.dto.OrderDetailDTO;
import com.le.flashsale.order.service.OrderService;

/**
 * Date 2020/11/17 11:09 上午
 * Author le
 */
@RestController
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    @Resource
    private ActivityInfoService activityInfoService;

    /**
     * 下单操作
     *
     * @param orderDTO
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/order/add")
    public BaseResponse addOrder(@RequestBody AddOrderDTO addOrderDTO) {
        BaseResponse response = BaseResponse.getSuccessResponse();
        if (!activityInfoService.activityValid(addOrderDTO.getActivityId())) {
            // TODO： 此处可以通过activityId将活动信息取出，完善提示信息。比如活动几点开始等等。
            return BaseResponse.getFailResponse("活动还未开始");
        }

        AddOrderResultDTO addOrderResultDTO = orderService.addOrder(addOrderDTO);
        response.setData(addOrderResultDTO);
        return response;
    }

    /**
     * 查看订单详情
     *
     * @param orderNo
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/order/query/{order_no}")
    public BaseResponse queryOrderDetail(@PathVariable("order_no") String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            logger.error("OrderController.addOrder订单号不能为空!");
            return BaseResponse.getFailResponse("订单号不能为空");
        }
        BaseResponse response = BaseResponse.getSuccessResponse();
        OrderDetailDTO orderDetailDTO = orderService.queryOrderInfoByOrderNo(orderNo);
        if (orderDetailDTO == null) {
            logger.error("OrderController.addOrder订单号不能为空!");
            return BaseResponse.getFailResponse("对不起，查询不到订单号为" + orderNo + "的订单");
        }
        orderDetailDTO.setStatusName(OrderEnums.getEnums(orderDetailDTO.getStatus()).getRemark());
        response.setData(orderDetailDTO);
        return response;
    }

}
