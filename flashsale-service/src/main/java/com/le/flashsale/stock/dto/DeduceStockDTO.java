
package com.le.flashsale.stock.dto;

/**
 * Date 2020/11/19 1:50 下午
 * Author le
 */
public class DeduceStockDTO {
    // 库存id
    private Long stockId;

    // 扣减数量
    private Integer nums;

    // 操作用户
    private Long userId;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
