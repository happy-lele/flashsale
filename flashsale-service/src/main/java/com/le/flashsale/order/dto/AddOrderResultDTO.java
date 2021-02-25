
package com.le.flashsale.order.dto;

import org.apache.logging.log4j.util.Strings;

/**
 * Date 2020/11/17 8:34 下午
 * Author le
 */
public class AddOrderResultDTO {

    // 订单编号
    // private String orderNo = Strings.EMPTY;

    // 是否成功下单
    private boolean success = false;

    // 返回信息
    private String message = Strings.EMPTY;

//    public String getOrderNo() {
//        return orderNo;
//    }
//
//    public void setOrderNo(String orderNo) {
//        this.orderNo = orderNo;
//    }

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
