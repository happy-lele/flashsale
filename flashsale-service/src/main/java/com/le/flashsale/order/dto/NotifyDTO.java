
package com.le.flashsale.order.dto;

/**
 * Date 2020/11/18 12:03 上午
 * Author le
 */
public class NotifyDTO {

    // 用户id
    private Long userId;

    // 订单号
    private String orderNo;

    // 订单id
    private Long orderId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
