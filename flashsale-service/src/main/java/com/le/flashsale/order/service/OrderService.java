
/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.le.flashsale.order.service;

import com.le.flashsale.order.dto.AddOrderDTO;
import com.le.flashsale.order.dto.AddOrderResultDTO;
import com.le.flashsale.order.dto.OrderDetailDTO;
import com.le.flashsale.order.dto.ProcessOrderDto;

/**
 * Date 2020/11/16 6:46 下午
 * Author le
 */
public interface OrderService {

    AddOrderResultDTO addOrder(AddOrderDTO addOrderDTO);

    void processOrder(ProcessOrderDto processOrderDto);

    OrderDetailDTO queryOrderInfoByOrderNo(String orderNo);

    // void notifyOrderInfo(NotifyDTO notifyDTO);
}
