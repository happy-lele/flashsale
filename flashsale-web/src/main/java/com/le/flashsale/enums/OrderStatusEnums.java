
package com.le.flashsale.enums;

/**
 * 订单状态枚举类
 * <p>
 * Date 2020/11/20 9:50 下午
 * Author le
 */
public enum OrderStatusEnums {

    INVALID(-1, "无效"),
    SUCCESS(0, "成功（待付款）"),
    COMPLETED(1, "完成（已付款）"),
    CANCELED(2, "已取消");

    private Integer code;
    private String msg;

    OrderStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
