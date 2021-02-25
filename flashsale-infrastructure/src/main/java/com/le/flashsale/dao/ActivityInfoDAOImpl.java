
package com.le.flashsale.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.le.flashsale.converter.ActivityInfoConverter;
import com.le.flashsale.entity.ActivityInfoPO;
import com.le.flashsale.mapper.ActivityInfoMapper;
import com.le.flashsale.activity.dao.ActivityInfoDAO;
import com.le.flashsale.activity.dto.ActivityInfoDTO;

/**
 * Date 2020/11/16 7:10 下午
 * Author le
 */
@Service
public class ActivityInfoDAOImpl implements ActivityInfoDAO {

    @Resource
    private ActivityInfoMapper activityInfoMapper;

    @Resource
    private ActivityInfoConverter converter;

    public int deleteByPrimaryKey(Long id) {
        return activityInfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(ActivityInfoDTO activityInfoDTO) {
        ActivityInfoPO activityInfoPO = converter.dto2Po(activityInfoDTO);
        int num = activityInfoMapper.insert(activityInfoPO);
        activityInfoDTO.setId(activityInfoPO.getId());
        return num;
    }

    public int insertSelective(ActivityInfoDTO activityInfoDTO) {
        ActivityInfoPO activityInfoPO = converter.dto2Po(activityInfoDTO);
        int num = activityInfoMapper.insertSelective(activityInfoPO);
        activityInfoDTO.setId(activityInfoPO.getId());
        return num;
    }

    public ActivityInfoDTO selectByPrimaryKey(Long id) {
        return converter.po2Dto(activityInfoMapper.selectByPrimaryKey(id));
    }

    public List<ActivityInfoDTO> selectAllEffectiveActivity() {
        return converter.pos2Dtos(activityInfoMapper.selectAllEffectiveActivity());
    }

    public int updateByPrimaryKeySelective(ActivityInfoDTO activityInfoDTO) {
        return activityInfoMapper.updateByPrimaryKeySelective(converter.dto2Po(activityInfoDTO));
    }

    public int updateByPrimaryKey(ActivityInfoDTO activityInfoDTO) {
        return activityInfoMapper.updateByPrimaryKey(converter.dto2Po(activityInfoDTO));
    }

    public int deduceNumsByPrimaryKey(Long id) {
        return activityInfoMapper.deduceNumsByPrimaryKey(id);
    }

    @Override
    public int increaseNumsByPrimaryKey(Long id) {
        return activityInfoMapper.increaseNumsByPrimaryKey(id);
    }
}
