package com.le.flashsale.converter;

import com.le.flashsale.entity.StockPO;
import com.le.flashsale.stock.dto.StockDTO;
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
public class StockConverterImpl implements StockConverter {

    @Override
    public StockPO dto2Po(StockDTO stockDTO) {
        if ( stockDTO == null ) {
            return null;
        }

        StockPO stockPO = new StockPO();

        stockPO.setId( stockDTO.getId() );
        stockPO.setProductId( stockDTO.getProductId() );
        stockPO.setTotalNums( stockDTO.getTotalNums() );
        stockPO.setFrozenNums( stockDTO.getFrozenNums() );
        stockPO.setActiveNums( stockDTO.getActiveNums() );
        stockPO.setDeleted( stockDTO.getDeleted() );
        stockPO.setCreateTime( stockDTO.getCreateTime() );
        stockPO.setUpdateTime( stockDTO.getUpdateTime() );

        return stockPO;
    }

    @Override
    public StockDTO po2Dto(StockPO stockPO) {
        if ( stockPO == null ) {
            return null;
        }

        StockDTO stockDTO = new StockDTO();

        stockDTO.setId( stockPO.getId() );
        stockDTO.setProductId( stockPO.getProductId() );
        stockDTO.setTotalNums( stockPO.getTotalNums() );
        stockDTO.setFrozenNums( stockPO.getFrozenNums() );
        stockDTO.setActiveNums( stockPO.getActiveNums() );
        stockDTO.setDeleted( stockPO.getDeleted() );
        stockDTO.setCreateTime( stockPO.getCreateTime() );
        stockDTO.setUpdateTime( stockPO.getUpdateTime() );

        return stockDTO;
    }

    @Override
    public List<StockDTO> pos2Dtos(List<StockPO> stockPO) {
        if ( stockPO == null ) {
            return null;
        }

        List<StockDTO> list = new ArrayList<StockDTO>( stockPO.size() );
        for ( StockPO stockPO1 : stockPO ) {
            list.add( po2Dto( stockPO1 ) );
        }

        return list;
    }
}
