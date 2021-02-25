
package com.le.flashsale.stock.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.le.flashsale.adapter.DistributeLockAdapter;
import com.le.flashsale.common.Constant;
import com.le.flashsale.common.validator.StockServiceValidator;
import com.le.flashsale.stock.dao.StockDAO;
import com.le.flashsale.stock.dto.DeduceStockDTO;
import com.le.flashsale.stock.dto.DeduceStockResultDTO;
import com.le.flashsale.stock.dto.StockDTO;
import com.le.flashsale.stock.dto.StockProductDTO;
import com.le.flashsale.stock.service.StockService;

/**
 * Date 2020/11/16 6:47 下午
 * Author le
 */
@Service
public class StockServiceImpl implements StockService {

    private Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Resource
    private DistributeLockAdapter distributeLockAdapter;

    @Resource
    private StockDAO stockDAO;

    @Override
    public List<StockProductDTO> getAllStock() {
        return stockDAO.getAllStock();
    }

    @Override
    public StockDTO getStockDetail(Long stockId) {
        return stockDAO.selectByPrimaryKey(stockId);
    }

    /**
     * 扣减库存。
     * 应用场景：新增一个秒杀活动时，需要从已有库存中扣减出一部分需要做秒杀活动的商品量。
     * <p>
     * 1> 当从下拉列表中选择了库存中的某个商品后，会显示当前该商品的可以使用（active_nums）的库存总量，
     * 2> 输入活动商品数量，点击新建秒杀活动。保证数据的数量不能超过可使用的库存数量。
     * 3> 扣减库存，将输入的活动商品数量增加冻结数量（frozen_nums）中，然后扣减可使用库存数量（active_nums）。————加分布式锁，防止数据被同时修改
     * 4> 扩展点；此处应该有库存详情表（tb_stock_detail）,来记录每次对库存的扣减或增加的明细行为。主要数据包含：stock_id, 操作人id，操作时间，操作数量。
     *
     * @param deduceStockDTO
     * @return
     */
    @Override
    public DeduceStockResultDTO deduceStock(DeduceStockDTO deduceStockDTO) {
        DeduceStockResultDTO deduceStockResultDTO = new DeduceStockResultDTO();
        // 校验参数是否正确
        String errorMsg;
        if (Strings.isNotBlank(errorMsg = StockServiceValidator.getDeduceStockErrorMsg(deduceStockDTO))) {
            deduceStockResultDTO.setMessage(errorMsg);
            return deduceStockResultDTO;
        }

        String lockKey = Constant.REDIS_PREFIX_STOCK_ + deduceStockDTO.getStockId();
        try {
            // 分布式锁操作
            if (distributeLockAdapter.lock(lockKey)) {
                StockDTO stockDTO = stockDAO.selectByPrimaryKey(deduceStockDTO.getStockId());
                // 通过库存id，查找不到库存商品
                if (stockDTO == null) {
                    deduceStockResultDTO.setMessage(Constant.MESSAGE_STOCK_FAILED_STOCK_NULL);
                    return deduceStockResultDTO;
                }
                // 可扣减的库存数量，小于待扣减的库存数量
                if (stockDTO.getActiveNums() < deduceStockDTO.getNums()) {
                    deduceStockResultDTO.setMessage(Constant.MESSAGE_STOCK_FAILED_NUMS_NOT_ENOUGH);
                    return deduceStockResultDTO;
                }

                // 执行扣减操作
                stockDAO.deduceStockById(deduceStockDTO.getStockId(), deduceStockDTO.getNums());
            }
        } catch (Throwable e) {
            logger.error("StockServiceImpl.deduceStock is error!", e);
        } finally {
            distributeLockAdapter.unlock(lockKey);
        }

        deduceStockResultDTO.setSuccess(true);
        return deduceStockResultDTO;
    }
}
