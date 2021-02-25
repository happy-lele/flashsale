package com.le.flashsale.converter;

import com.le.flashsale.entity.OrderDetailPO;
import com.le.flashsale.entity.OrderPO;
import com.le.flashsale.order.dto.OrderDTO;
import com.le.flashsale.order.dto.OrderDetailDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:39:36+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class OrderConverterImpl implements OrderConverter {

    @Override
    public OrderPO dto2Po(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        OrderPO orderPO = new OrderPO();

        orderPO.setId( orderDTO.getId() );
        orderPO.setOrderNo( orderDTO.getOrderNo() );
        orderPO.setProductId( orderDTO.getProductId() );
        orderPO.setActivityId( orderDTO.getActivityId() );
        orderPO.setUserId( orderDTO.getUserId() );
        orderPO.setStatus( orderDTO.getStatus() );
        orderPO.setCreateTime( orderDTO.getCreateTime() );
        orderPO.setUpdateTime( orderDTO.getUpdateTime() );

        return orderPO;
    }

    @Override
    public OrderDTO po2Dto(OrderPO orderPO) {
        if ( orderPO == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( orderPO.getId() );
        orderDTO.setOrderNo( orderPO.getOrderNo() );
        orderDTO.setProductId( orderPO.getProductId() );
        orderDTO.setActivityId( orderPO.getActivityId() );
        orderDTO.setUserId( orderPO.getUserId() );
        orderDTO.setStatus( orderPO.getStatus() );
        orderDTO.setCreateTime( orderPO.getCreateTime() );
        orderDTO.setUpdateTime( orderPO.getUpdateTime() );

        return orderDTO;
    }

    @Override
    public OrderDetailDTO po2Dto(OrderDetailPO orderDetailPO) {
        if ( orderDetailPO == null ) {
            return null;
        }

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setId( orderDetailPO.getId() );
        orderDetailDTO.setOrderNo( orderDetailPO.getOrderNo() );
        orderDetailDTO.setProductId( orderDetailPO.getProductId() );
        orderDetailDTO.setName( orderDetailPO.getName() );
        orderDetailDTO.setUserId( orderDetailPO.getUserId() );
        orderDetailDTO.setUserName( orderDetailPO.getUserName() );
        orderDetailDTO.setStatus( orderDetailPO.getStatus() );
        orderDetailDTO.setCreateTime( orderDetailPO.getCreateTime() );
        orderDetailDTO.setUpdateTime( orderDetailPO.getUpdateTime() );

        return orderDetailDTO;
    }
}
