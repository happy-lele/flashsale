
package com.le.flashsale.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.le.flashsale.user.dao.UserDAO;
import com.le.flashsale.user.dto.UserDTO;
import com.le.flashsale.user.service.UserService;

/**
 * Date 2020/11/16 6:47 下午
 * Author le
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public UserDTO queryUserInfoById(Long userId) {
        return userDAO.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        return userDAO.insert(userDTO) > 0;
    }
}
