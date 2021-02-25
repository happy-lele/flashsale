
package com.le.flashsale.activity.service;

import java.util.List;

import com.le.flashsale.activity.dto.ActivityInfoDTO;

/**
 * Date 2020/11/16 6:46 下午
 * Author le
 */
public interface ActivityInfoService {

    List<ActivityInfoDTO> queryAllEffectiveActivity();

    boolean addActivityInfo(ActivityInfoDTO activityInfoDTO);

    void deleteActivityInfo(Long activityId);

    void deduceProductNums(Long activityId);

    void increaseProductNums(Long activityId);

    /**
     * 活动是否已经发起了
     *
     * @param activityId
     * @return
     */
    boolean activityValid(Long activityId);
}
