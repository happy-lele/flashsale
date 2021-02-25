package com.le.flashsale.converter;

import com.le.flashsale.entity.StockProductPO;
import com.le.flashsale.stock.dto.StockProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:39:36+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class StockProductConverterImpl implements StockProductConverter {

    @Override
    public StockProductPO dto2Po(StockProductDTO stockProductDTO) {
        if ( stockProductDTO == null ) {
            return null;
        }

        StockProductPO stockProductPO = new StockProductPO();

        stockProductPO.setId( stockProductDTO.getId() );
        stockProductPO.setProductId( stockProductDTO.getProductId() );
        stockProductPO.setProductName( stockProductDTO.getProductName() );
        stockProductPO.setRemark( stockProductDTO.getRemark() );
        stockProductPO.setTotalNums( stockProductDTO.getTotalNums() );
        stockProductPO.setFrozenNums( stockProductDTO.getFrozenNums() );
        stockProductPO.setActiveNums( stockProductDTO.getActiveNums() );

        return stockProductPO;
    }

    @Override
    public StockProductDTO po2Dto(StockProductPO stockProductPO) {
        if ( stockProductPO == null ) {
            return null;
        }

        StockProductDTO stockProductDTO = new StockProductDTO();

        stockProductDTO.setId( stockProductPO.getId() );
        stockProductDTO.setProductId( stockProductPO.getProductId() );
        stockProductDTO.setProductName( stockProductPO.getProductName() );
        stockProductDTO.setRemark( stockProductPO.getRemark() );
        stockProductDTO.setTotalNums( stockProductPO.getTotalNums() );
        stockProductDTO.setFrozenNums( stockProductPO.getFrozenNums() );
        stockProductDTO.setActiveNums( stockProductPO.getActiveNums() );

        return stockProductDTO;
    }

    @Override
    public List<StockProductDTO> pos2Dtos(List<StockProductPO> stockProductPOS) {
        if ( stockProductPOS == null ) {
            return null;
        }

        List<StockProductDTO> list = new ArrayList<StockProductDTO>( stockProductPOS.size() );
        for ( StockProductPO stockProductPO : stockProductPOS ) {
            list.add( po2Dto( stockProductPO ) );
        }

        return list;
    }
}
