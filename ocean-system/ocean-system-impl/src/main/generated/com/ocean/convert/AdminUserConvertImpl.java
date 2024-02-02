package com.ocean.convert;

import com.ocean.model.entity.AdminUserDO;
import com.ocean.model.vo.AdminUserPageResVO;
import com.ocean.model.vo.AdminUserVO;
import com.ocean.model.vo.UserCreateVO;
import com.ocean.model.vo.UserRegisterVO;
import com.ocean.model.vo.auth.AuthenticatedUserVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T16:07:42+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_391 (Oracle Corporation)"
)
public class AdminUserConvertImpl implements AdminUserConvert {

    @Override
    public AdminUserPageResVO convert(AdminUserDO item) {
        if ( item == null ) {
            return null;
        }

        AdminUserPageResVO adminUserPageResVO = new AdminUserPageResVO();

        adminUserPageResVO.setId( item.getId() );
        adminUserPageResVO.setUsername( item.getUsername() );
        adminUserPageResVO.setNickname( item.getNickname() );
        adminUserPageResVO.setTelephone( item.getTelephone() );
        adminUserPageResVO.setCreateTime( item.getCreateTime() );

        return adminUserPageResVO;
    }

    @Override
    public AdminUserVO DOtoAdminUserVO(AdminUserDO adminUserDO) {
        if ( adminUserDO == null ) {
            return null;
        }

        AdminUserVO adminUserVO = new AdminUserVO();

        adminUserVO.setId( adminUserDO.getId() );
        adminUserVO.setUsername( adminUserDO.getUsername() );
        adminUserVO.setPassword( adminUserDO.getPassword() );
        adminUserVO.setNickname( adminUserDO.getNickname() );
        adminUserVO.setEmail( adminUserDO.getEmail() );
        adminUserVO.setTelephone( adminUserDO.getTelephone() );
        adminUserVO.setRemark( adminUserDO.getRemark() );

        return adminUserVO;
    }

    @Override
    public AuthenticatedUserVO DOtoAuthenticatedUserVO(AdminUserDO adminUserDO) {
        if ( adminUserDO == null ) {
            return null;
        }

        AuthenticatedUserVO authenticatedUserVO = new AuthenticatedUserVO();

        authenticatedUserVO.setUserId( adminUserDO.getId() );
        authenticatedUserVO.setUsername( adminUserDO.getUsername() );
        authenticatedUserVO.setNickname( adminUserDO.getNickname() );

        return authenticatedUserVO;
    }

    @Override
    public UserCreateVO RegisterTOCreate(UserRegisterVO userRegisterVO) {
        if ( userRegisterVO == null ) {
            return null;
        }

        UserCreateVO userCreateVO = new UserCreateVO();

        userCreateVO.setUsername( userRegisterVO.getUsername() );
        userCreateVO.setPassword( userRegisterVO.getPassword() );

        return userCreateVO;
    }
}
