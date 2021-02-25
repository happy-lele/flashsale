
/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.le.flashsale.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.le.flashsale.converter.OrderConverter;
import com.le.flashsale.entity.OrderPO;
import com.le.flashsale.mapper.OrderMapper;
import com.le.flashsale.order.dao.OrderDAO;
import com.le.flashsale.order.dto.OrderDTO;
import com.le.flashsale.order.dto.OrderDetailDTO;

/**
 * Date 2020/11/16 6:59 下午
 * Author le
 */
@Service
public class OrderDAOImpl implements OrderDAO {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderConverter converter;

    public int deleteByPrimaryKey(Long id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    public int insert(OrderDTO orderDTO) {
        OrderPO orderPO = converter.dto2Po(orderDTO);
        int num = orderMapper.insert(orderPO);
        orderDTO.setId(orderPO.getId());
        return num;
    }

    public int insertSelective(OrderDTO orderDTO) {
        OrderPO orderPO = converter.dto2Po(orderDTO);
        int num = orderMapper.insert(orderPO);
        orderDTO.setId(orderPO.getId());
        return num;
    }

    public OrderDTO selectByPrimaryKey(Long id) {
        return converter.po2Dto(orderMapper.selectByPrimaryKey(id));
    }

    public int updateByPrimaryKeySelective(OrderDTO orderDTO) {
        return orderMapper.updateByPrimaryKeySelective(converter.dto2Po(orderDTO));
    }

    public int updateByPrimaryKey(OrderDTO orderDTO) {
        return orderMapper.updateByPrimaryKey(converter.dto2Po(orderDTO));
    }

    public OrderDetailDTO selectByOrderNo(String orderNo) {
        return converter.po2Dto(orderMapper.selectByOrderNo(orderNo));
    }
}
