
package com.le.flashsale.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.le.flashsale.entity.OrderDetailPO;
import com.le.flashsale.entity.OrderPO;
import com.le.flashsale.order.dto.OrderDTO;
import com.le.flashsale.order.dto.OrderDetailDTO;

/**
 * Date 2020/11/16 4:16 下午
 * Author le
 */
@Mapper(componentModel = "spring")
public interface OrderConverter {

    OrderConverter instance = Mappers.getMapper(OrderConverter.class);

    OrderPO dto2Po(OrderDTO orderDTO);

    OrderDTO po2Dto(OrderPO orderPO);

    OrderDetailDTO po2Dto(OrderDetailPO orderDetailPO);
}
