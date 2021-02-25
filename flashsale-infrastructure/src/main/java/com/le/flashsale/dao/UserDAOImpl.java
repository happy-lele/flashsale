
package com.le.flashsale.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.le.flashsale.converter.UserConverter;
import com.le.flashsale.entity.UserPO;
import com.le.flashsale.mapper.UserMapper;
import com.le.flashsale.user.dao.UserDAO;
import com.le.flashsale.user.dto.UserDTO;

/**
 * Date 2020/11/16 7:13 下午
 * Author le
 */
@Service
public class UserDAOImpl implements UserDAO {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserConverter converter;

    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int insert(UserDTO userDTO) {
        UserPO userPO = converter.dto2Po(userDTO);
        int num = userMapper.insert(userPO);
        userDTO.setId(userPO.getId());
        return num;
    }

    public int insertSelective(UserDTO userDTO) {
        UserPO userPO = converter.dto2Po(userDTO);
        int num = userMapper.insertSelective(userPO);
        userDTO.setId(userPO.getId());
        return num;
    }

    public UserDTO selectByPrimaryKey(Long id) {
        return converter.po2Dto(userMapper.selectByPrimaryKey(id));
    }

    public int updateByPrimaryKeySelective(UserDTO userDTO) {
        return userMapper.updateByPrimaryKeySelective(converter.dto2Po(userDTO));
    }

    public int updateByPrimaryKey(UserDTO userDTO) {
        return userMapper.updateByPrimaryKey(converter.dto2Po(userDTO));
    }
}
