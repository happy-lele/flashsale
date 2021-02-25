
package com.le.flashsale.stock.service;

import java.util.List;

import com.le.flashsale.stock.dto.DeduceStockDTO;
import com.le.flashsale.stock.dto.DeduceStockResultDTO;
import com.le.flashsale.stock.dto.StockDTO;
import com.le.flashsale.stock.dto.StockProductDTO;

/**
 * Date 2020/11/16 6:47 下午
 * Author le
 */
public interface StockService {

    /**
     * 获得所有库存列表
     *
     * @return
     */
    List<StockProductDTO> getAllStock();

    /**
     * 获得某个库存商品明细
     *
     * @param stockId
     * @return
     */
    StockDTO getStockDetail(Long stockId);

    /**
     * 扣减库存
     *
     * @param deduceStockDTO
     * @return
     */
    DeduceStockResultDTO deduceStock(DeduceStockDTO deduceStockDTO);
}
