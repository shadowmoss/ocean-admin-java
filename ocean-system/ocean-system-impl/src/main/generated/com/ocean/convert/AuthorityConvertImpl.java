package com.ocean.convert;

import com.ocean.model.entity.MenuDO;
import com.ocean.model.vo.permission.AuthorityMenuVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-29T22:12:37+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_391 (Oracle Corporation)"
)
public class AuthorityConvertImpl implements AuthorityConvert {

    @Override
    public AuthorityMenuVO MenuDOtoAuthorityVO(MenuDO item) {
        if ( item == null ) {
            return null;
        }

        AuthorityMenuVO authorityMenuVO = new AuthorityMenuVO();

        authorityMenuVO.setId( item.getId() );
        authorityMenuVO.setParentId( item.getParentId() );
        authorityMenuVO.setType( item.getType() );
        authorityMenuVO.setName( item.getName() );
        authorityMenuVO.setPath( item.getPath() );
        authorityMenuVO.setComponentName( item.getComponentName() );
        authorityMenuVO.setComponent( item.getComponent() );
        authorityMenuVO.setIcon( item.getIcon() );
        authorityMenuVO.setVisible( item.getVisible() );
        authorityMenuVO.setKeepAlive( item.getKeepAlive() );
        authorityMenuVO.setAlwaysShow( item.getAlwaysShow() );

        return authorityMenuVO;
    }
}
