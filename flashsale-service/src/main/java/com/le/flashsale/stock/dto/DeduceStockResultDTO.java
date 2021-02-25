
package com.le.flashsale.stock.dto;

import org.apache.logging.log4j.util.Strings;

/**
 * Date 2020/11/19 3:44 下午
 * Author le
 */
public class DeduceStockResultDTO {

    private boolean success = false;

    private String message = Strings.EMPTY;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
