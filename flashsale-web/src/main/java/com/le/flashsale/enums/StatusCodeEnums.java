
package com.le.flashsale.enums;

/**
 * Date 2020/11/16 8:33 上午
 * Author le
 */
public enum StatusCodeEnums {

    SUCCESS(0,"成功"),
    FAIL(-1,"失败");

    private Integer code;
    private String msg;

    StatusCodeEnums(Integer code, String msg) {
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
