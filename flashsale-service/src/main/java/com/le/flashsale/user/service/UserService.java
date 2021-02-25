
package com.le.flashsale.user.service;

import com.le.flashsale.user.dto.UserDTO;

/**
 * Date 2020/11/16 6:47 下午
 * Author le
 */
public interface UserService {

    /**
     * 通过id查找用户详情信息，包含联系方式等
     *
     * @param userId
     * @return
     */
    UserDTO queryUserInfoById(Long userId);

    /**
     * 新增用户
     *
     * @param userDTO
     * @return
     */
    boolean addUser(UserDTO userDTO);

    /**
     * 通过userid来删除用户（不是重点，省略）
     *
     * @param userId
     * @return
     */
    // boolean delUserById(Long userId);

    /**
     * 修改用户（不是重点，省略）
     *
     * @param userDTO
     * @return
     */
    // boolean modifyUser(UserDTO userDTO);
}
