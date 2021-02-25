
package com.le.flashsale.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.le.flashsale.common.Constant;
import com.le.flashsale.dto.BaseResponse;
import com.le.flashsale.stock.service.StockService;

/**
 * Date 2020/11/19 3:26 下午
 * Author le
 */
@RestController
public class StockController {

    @Resource
    private StockService stockService;

    /**
     * 获得库存列表
     *
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/stock/list")
    public BaseResponse getAllStockList() {
        BaseResponse baseResponse = BaseResponse.getSuccessResponse();
        baseResponse.setData(stockService.getAllStock());

        return baseResponse;
    }

    /**
     * 获得库存详情
     *
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/stock/detail/{stock_id}")
    public BaseResponse getAllStockList(@PathVariable("stock_id") Long stockId) {
        if (stockId == null) {
            return BaseResponse.getFailResponse(Constant.MESSAGE_STOCK_FAILED_STOCKID_NULL);
        }

        BaseResponse baseResponse = BaseResponse.getSuccessResponse();
        baseResponse.setData(stockService.getStockDetail(stockId));

        return baseResponse;
    }

}
