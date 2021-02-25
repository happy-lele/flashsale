
package com.le.flashsale.order.dto;

/**
 * Date 2020/11/17 10:40 下午
 * Author le
 */
public class ProcessOrderDto {

    private String orderNo;

    private Long userId;

    private Long productId;

    private Long activityId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
