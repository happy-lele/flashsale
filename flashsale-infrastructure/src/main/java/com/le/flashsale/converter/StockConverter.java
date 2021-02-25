
package com.le.flashsale.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.le.flashsale.entity.StockPO;
import com.le.flashsale.stock.dto.StockDTO;

/**
 * Date 2020/11/16 4:16 下午
 * Author le
 */
@Mapper(componentModel = "spring")
public interface StockConverter {

    StockConverter instance = Mappers.getMapper(StockConverter.class);

    StockPO dto2Po(StockDTO stockDTO);

    StockDTO po2Dto(StockPO stockPO);

    List<StockDTO> pos2Dtos(List<StockPO> stockPO);
}
