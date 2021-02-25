package com.le.flashsale.converter;

import com.le.flashsale.entity.UserPO;
import com.le.flashsale.user.dto.UserDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:39:36+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserPO dto2Po(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserPO userPO = new UserPO();

        userPO.setId( userDTO.getId() );
        userPO.setUserName( userDTO.getUserName() );
        userPO.setPassword( userDTO.getPassword() );
        userPO.setPhoneNumber( userDTO.getPhoneNumber() );
        userPO.setEmail( userDTO.getEmail() );
        userPO.setDeleted( userDTO.getDeleted() );
        userPO.setCreateTime( userDTO.getCreateTime() );
        userPO.setUpdateTime( userDTO.getUpdateTime() );

        return userPO;
    }

    @Override
    public UserDTO po2Dto(UserPO userPO) {
        if ( userPO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( userPO.getId() );
        userDTO.setUserName( userPO.getUserName() );
        userDTO.setPassword( userPO.getPassword() );
        userDTO.setPhoneNumber( userPO.getPhoneNumber() );
        userDTO.setEmail( userPO.getEmail() );
        userDTO.setDeleted( userPO.getDeleted() );
        userDTO.setCreateTime( userPO.getCreateTime() );
        userDTO.setUpdateTime( userPO.getUpdateTime() );

        return userDTO;
    }
}
