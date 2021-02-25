package com.le.flashsale.converter;

import com.le.flashsale.activity.dto.ActivityInfoDTO;
import com.le.flashsale.entity.ActivityInfoPO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:39:36+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class ActivityInfoConverterImpl implements ActivityInfoConverter {

    @Override
    public ActivityInfoPO dto2Po(ActivityInfoDTO activityInfoDTO) {
        if ( activityInfoDTO == null ) {
            return null;
        }

        ActivityInfoPO activityInfoPO = new ActivityInfoPO();

        activityInfoPO.setId( activityInfoDTO.getId() );
        activityInfoPO.setProductId( activityInfoDTO.getProductId() );
        activityInfoPO.setPrice( activityInfoDTO.getPrice() );
        activityInfoPO.setNums( activityInfoDTO.getNums() );
        activityInfoPO.setStartTime( activityInfoDTO.getStartTime() );
        activityInfoPO.setEndTime( activityInfoDTO.getEndTime() );
        activityInfoPO.setDeleted( activityInfoDTO.getDeleted() );
        activityInfoPO.setCreateTime( activityInfoDTO.getCreateTime() );
        activityInfoPO.setUpdateTime( activityInfoDTO.getUpdateTime() );

        return activityInfoPO;
    }

    @Override
    public ActivityInfoDTO po2Dto(ActivityInfoPO activityInfoPO) {
        if ( activityInfoPO == null ) {
            return null;
        }

        ActivityInfoDTO activityInfoDTO = new ActivityInfoDTO();

        activityInfoDTO.setId( activityInfoPO.getId() );
        activityInfoDTO.setProductId( activityInfoPO.getProductId() );
        activityInfoDTO.setPrice( activityInfoPO.getPrice() );
        activityInfoDTO.setNums( activityInfoPO.getNums() );
        activityInfoDTO.setStartTime( activityInfoPO.getStartTime() );
        activityInfoDTO.setEndTime( activityInfoPO.getEndTime() );
        activityInfoDTO.setDeleted( activityInfoPO.getDeleted() );
        activityInfoDTO.setCreateTime( activityInfoPO.getCreateTime() );
        activityInfoDTO.setUpdateTime( activityInfoPO.getUpdateTime() );

        return activityInfoDTO;
    }

    @Override
    public List<ActivityInfoDTO> pos2Dtos(List<ActivityInfoPO> activityInfoPOs) {
        if ( activityInfoPOs == null ) {
            return null;
        }

        List<ActivityInfoDTO> list = new ArrayList<ActivityInfoDTO>( activityInfoPOs.size() );
        for ( ActivityInfoPO activityInfoPO : activityInfoPOs ) {
            list.add( po2Dto( activityInfoPO ) );
        }

        return list;
    }
}
