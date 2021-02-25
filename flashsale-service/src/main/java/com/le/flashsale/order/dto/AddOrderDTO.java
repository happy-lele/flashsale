
package com.le.flashsale.order.dto;

/**
 * Date 2020/11/17 9:25 下午
 * Author le
 */
public class AddOrderDTO {

    // 产品id
    private Long productId;

    // 用户id, 登录后，从session中获取来的。[登录态］——> gateway ——> web层
    private Long userId;

    // 活动id
    private Long activityId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
