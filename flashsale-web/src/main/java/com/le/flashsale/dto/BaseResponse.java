
package com.le.flashsale.dto;

import com.le.flashsale.enums.StatusCodeEnums;

/**
 * Date 2020/11/16 8:32 上午
 * Author le
 */
public class BaseResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(StatusCodeEnums statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResponse getSuccessResponse() {
        return new BaseResponse(StatusCodeEnums.SUCCESS.getCode(), "SUCCESS");
    }

    public static BaseResponse getSuccessResponse(String msg) {
        return new BaseResponse(StatusCodeEnums.SUCCESS.getCode(), msg);
    }

    public static BaseResponse getFailResponse() {
        return new BaseResponse(StatusCodeEnums.FAIL.getCode(), "FAIL");
    }

    public static BaseResponse getFailResponse(String msg) {
        return new BaseResponse(StatusCodeEnums.FAIL.getCode(), msg);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
