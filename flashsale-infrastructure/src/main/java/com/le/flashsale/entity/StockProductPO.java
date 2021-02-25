/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.le.flashsale.entity;

/**
 * Date 2020/11/28 8:39 上午
 */
public class StockProductPO {

    private Long id;

    private Long productId;

    private String productName;

    private String remark;

    private Long totalNums;

    private Long frozenNums;

    private Long activeNums;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(Long totalNums) {
        this.totalNums = totalNums;
    }

    public Long getFrozenNums() {
        return frozenNums;
    }

    public void setFrozenNums(Long frozenNums) {
        this.frozenNums = frozenNums;
    }

    public Long getActiveNums() {
        return activeNums;
    }

    public void setActiveNums(Long activeNums) {
        this.activeNums = activeNums;
    }
}
