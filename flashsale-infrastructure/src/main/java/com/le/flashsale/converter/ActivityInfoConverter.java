
package com.le.flashsale.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.le.flashsale.entity.ActivityInfoPO;
import com.le.flashsale.activity.dto.ActivityInfoDTO;

/**
 * Date 2020/11/16 4:16 下午
 * Author le
 */
@Mapper(componentModel = "spring")
public interface ActivityInfoConverter {

    ActivityInfoConverter instance = Mappers.getMapper(ActivityInfoConverter.class);

    ActivityInfoPO dto2Po(ActivityInfoDTO activityInfoDTO);

    ActivityInfoDTO po2Dto(ActivityInfoPO activityInfoPO);

    List<ActivityInfoDTO> pos2Dtos(List<ActivityInfoPO> activityInfoPOs);

}
