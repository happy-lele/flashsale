
package com.le.flashsale.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.le.flashsale.entity.UserPO;
import com.le.flashsale.user.dto.UserDTO;

/**
 * Date 2020/11/16 4:16 下午
 * Author le
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserConverter instance = Mappers.getMapper(UserConverter.class);

    UserPO dto2Po(UserDTO userDTO);

    UserDTO po2Dto(UserPO userPO);
}
