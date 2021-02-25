
package com.le.flashsale.common.enums;

/**
 * Date 2020/11/17 11:13 下午
 * Author le
 */
public enum OrderEnums {

    UNKNOWN(-1, "未知状态"),
    WAIT_PAY(0, "等待支付"),
    PAYED(1, "已付款"),
    CANCEL(2, "已取消");

    private Integer code;
    private String remark;

    OrderEnums(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static OrderEnums getEnums(Integer code) {
        for (OrderEnums orderEnums : OrderEnums.values()) {
            if (orderEnums.code.equals(code)) {
                return orderEnums;
            }
        }
        return UNKNOWN;
    }
}
