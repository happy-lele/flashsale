
package com.le.flashsale.common.validator;

import org.apache.logging.log4j.util.Strings;

import com.le.flashsale.common.Constant;
import com.le.flashsale.stock.dto.DeduceStockDTO;

/**
 * Date 2020/11/19 3:51 下午
 * Author le
 */
public class StockServiceValidator {

    public static String getDeduceStockErrorMsg(DeduceStockDTO deduceStockDTO) {
        String errorMsg = Strings.EMPTY;
        if (deduceStockDTO == null) {
            errorMsg = Constant.MESSAGE_STOCK_BASE_PARAM_NULL;
        }
        if (deduceStockDTO.getUserId() == null) {
            errorMsg = Constant.MESSAGE_STOCK_FAILED_USERID_NULL;
        }
        if (deduceStockDTO.getStockId() == null) {
            errorMsg = Constant.MESSAGE_STOCK_FAILED_STOCKID_NULL;
        }
        if (deduceStockDTO.getNums() == null) {
            errorMsg = Constant.MESSAGE_STOCK_FAILED_NUMS_NULL;
        } else if (deduceStockDTO.getNums() < 0) {
            errorMsg = Constant.MESSAGE_STOCK_FAILED_NUMS_ERROR;
        }
        return errorMsg;
    }
}
