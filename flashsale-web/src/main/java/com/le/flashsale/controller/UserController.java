
/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.le.flashsale.controller;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.le.flashsale.common.Constant;
import com.le.flashsale.dto.BaseResponse;
import com.le.flashsale.enums.StatusCodeEnums;
import com.le.flashsale.user.dto.UserDTO;
import com.le.flashsale.user.service.UserService;

/**
 * Date 2020/11/19 3:26 下午
 * Author le
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增用户
     *
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/user/add")
    public BaseResponse addUser(@RequestBody UserDTO userDTO) {
        if (userDTO == null) {
            return BaseResponse.getFailResponse(Constant.MESSAGE_USER_FAILED_USER_NULL);
        }

        boolean success;
        BaseResponse baseResponse = BaseResponse.getSuccessResponse();
        try {
            success = userService.addUser(userDTO);
        } catch (DuplicateKeyException duplicateKeyException) {
            baseResponse.setCode(StatusCodeEnums.FAIL.getCode());
            baseResponse.setMsg("用户名:" + userDTO.getUserName() + ",已被其他人注册，请重新输入其他用户名");
            return baseResponse;
        }
        if (!success) {
            baseResponse.setCode(StatusCodeEnums.FAIL.getCode());
            baseResponse.setMsg("账号创建失败");
        }

        return baseResponse;
    }

    /**
     * 通过用户id获取用户信息。
     *
     * TODO 为了避免被其他人接口请求不同的userId，导致用户信息丢失。该userId应该从登录的session中获取。并且不提供web请求接口，只提供service接口访问
     *
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/user/detail/{user_id}")
    public BaseResponse getUserById(@PathVariable("user_id") Long userId) {
        if (userId == null) {
            return BaseResponse.getFailResponse(Constant.MESSAGE_USER_FAILED_STOCKID_NULL);
        }

        BaseResponse baseResponse = BaseResponse.getSuccessResponse();
        baseResponse.setData(userService.queryUserInfoById(userId));

        return baseResponse;
    }

}
