
package com.le.flashsale.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.le.flashsale.common.Constant;
import com.le.flashsale.dto.BaseResponse;
import com.le.flashsale.activity.dto.ActivityInfoDTO;
import com.le.flashsale.activity.service.ActivityInfoService;

/**
 * Date 2020/11/16 8:32 上午
 * Author le
 */
@RestController
public class ActivityInfoController {

    private static final Logger log = LoggerFactory.getLogger(ActivityInfoController.class);

    @Resource
    private ActivityInfoService activityInfoService;

    /**
     * 查询活动列表
     *
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/activity/list")
    public BaseResponse queryActivityList() {
        BaseResponse baseResponse = BaseResponse.getSuccessResponse();
        baseResponse.setData(activityInfoService.queryAllEffectiveActivity());
        return baseResponse;
    }

    /**
     * 新增活动
     *
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/activity/add", method = RequestMethod.POST)
    public BaseResponse addActivityInfo(@RequestBody ActivityInfoDTO activityInfoDTO) {
        if (activityInfoDTO == null) {
            return BaseResponse.getFailResponse("待新增的活动信息不能为空");
        }
        boolean success;
        try {
            success = activityInfoService.addActivityInfo(activityInfoDTO);
        } catch (Throwable e) {
            return BaseResponse.getFailResponse("新增的活动信息失败，请联系系统管理员处理。");
        }

        if (!success) {
            return BaseResponse.getFailResponse("新增的活动信息失败，请联系系统管理员处理。");
        }

        return BaseResponse.getSuccessResponse();
    }

    /**
     * 删除活动
     *
     * @return
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/activity/del", method = RequestMethod.POST)
    public BaseResponse delActivityInfo(@RequestBody ActivityInfoDTO activityInfoDTO) {
        if (activityInfoDTO == null || activityInfoDTO.getId() == null) {
            return BaseResponse.getFailResponse("待新增的活动信息不能为空");
        }

        BaseResponse baseResponse = BaseResponse.getSuccessResponse();
        Long activityId = activityInfoDTO.getId();
        activityInfoService.deleteActivityInfo(activityId);
        return baseResponse;
    }

    /**
     * TODO：更新活动（省略）
     */
    @RequestMapping(value = Constant.HTTP_PREFIX + "/activity/modify", method = RequestMethod.POST)
    public BaseResponse modifyActivityInfo(@RequestBody ActivityInfoDTO activityInfoDTO) {
        BaseResponse baseResponse = BaseResponse.getSuccessResponse();

        return baseResponse;
    }
}
