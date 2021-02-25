
/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.le.flashsale.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.le.flashsale.entity.StockProductPO;
import com.le.flashsale.stock.dto.StockProductDTO;

/**
 * Date 2020/11/16 4:16 下午
 * Author le
 */
@Mapper(componentModel = "spring")
public interface StockProductConverter {

    StockProductConverter instance = Mappers.getMapper(StockProductConverter.class);

    StockProductPO dto2Po(StockProductDTO stockProductDTO);

    StockProductDTO po2Dto(StockProductPO stockProductPO);

    List<StockProductDTO> pos2Dtos(List<StockProductPO> stockProductPOS);
}
