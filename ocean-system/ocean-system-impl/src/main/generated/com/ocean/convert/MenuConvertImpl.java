package com.ocean.convert;

import com.ocean.model.entity.MenuDO;
import com.ocean.model.vo.permission.MenuBaseVO;
import com.ocean.model.vo.permission.MenuCreateVO;
import com.ocean.model.vo.permission.MenuGetOneVO;
import com.ocean.model.vo.permission.MenuResVO;
import com.ocean.model.vo.permission.MenuUpdateVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-29T22:12:37+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_391 (Oracle Corporation)"
)
public class MenuConvertImpl implements MenuConvert {

    @Override
    public MenuDO createVOtoDO(MenuCreateVO menuCreateVO) {
        if ( menuCreateVO == null ) {
            return null;
        }

        MenuDO menuDO = new MenuDO();

        menuDO.setName( menuCreateVO.getName() );
        menuDO.setPermission( menuCreateVO.getPermission() );
        menuDO.setType( menuCreateVO.getType() );
        menuDO.setSort( menuCreateVO.getSort() );
        menuDO.setParentId( menuCreateVO.getParentId() );
        menuDO.setPath( menuCreateVO.getPath() );
        menuDO.setIcon( menuCreateVO.getIcon() );
        menuDO.setComponent( menuCreateVO.getComponent() );
        menuDO.setComponentName( menuCreateVO.getComponentName() );
        menuDO.setStatus( menuCreateVO.getStatus() );
        menuDO.setVisible( menuCreateVO.getVisible() );
        menuDO.setKeepAlive( menuCreateVO.getKeepAlive() );
        menuDO.setAlwaysShow( menuCreateVO.getAlwaysShow() );

        return menuDO;
    }

    @Override
    public MenuDO updateVOtoDO(MenuUpdateVO menuUpdateVO) {
        if ( menuUpdateVO == null ) {
            return null;
        }

        MenuDO menuDO = new MenuDO();

        menuDO.setId( menuUpdateVO.getId() );
        menuDO.setName( menuUpdateVO.getName() );
        menuDO.setPermission( menuUpdateVO.getPermission() );
        menuDO.setType( menuUpdateVO.getType() );
        menuDO.setSort( menuUpdateVO.getSort() );
        menuDO.setParentId( menuUpdateVO.getParentId() );
        menuDO.setPath( menuUpdateVO.getPath() );
        menuDO.setIcon( menuUpdateVO.getIcon() );
        menuDO.setComponent( menuUpdateVO.getComponent() );
        menuDO.setComponentName( menuUpdateVO.getComponentName() );
        menuDO.setStatus( menuUpdateVO.getStatus() );
        menuDO.setVisible( menuUpdateVO.getVisible() );
        menuDO.setKeepAlive( menuUpdateVO.getKeepAlive() );
        menuDO.setAlwaysShow( menuUpdateVO.getAlwaysShow() );

        return menuDO;
    }

    @Override
    public MenuResVO DOtoListVO(MenuDO item) {
        if ( item == null ) {
            return null;
        }

        MenuResVO menuResVO = new MenuResVO();

        menuResVO.setName( item.getName() );
        menuResVO.setPermission( item.getPermission() );
        menuResVO.setType( item.getType() );
        menuResVO.setSort( item.getSort() );
        menuResVO.setParentId( item.getParentId() );
        menuResVO.setPath( item.getPath() );
        menuResVO.setIcon( item.getIcon() );
        menuResVO.setComponent( item.getComponent() );
        menuResVO.setComponentName( item.getComponentName() );
        menuResVO.setStatus( item.getStatus() );
        menuResVO.setVisible( item.getVisible() );
        menuResVO.setKeepAlive( item.getKeepAlive() );
        menuResVO.setAlwaysShow( item.getAlwaysShow() );
        menuResVO.setId( item.getId() );
        menuResVO.setCreateTime( item.getCreateTime() );

        return menuResVO;
    }

    @Override
    public MenuBaseVO DOtoBaseVO(MenuDO menuDO) {
        if ( menuDO == null ) {
            return null;
        }

        MenuBaseVO menuBaseVO = new MenuBaseVO();

        menuBaseVO.setName( menuDO.getName() );
        menuBaseVO.setPermission( menuDO.getPermission() );
        menuBaseVO.setType( menuDO.getType() );
        menuBaseVO.setSort( menuDO.getSort() );
        menuBaseVO.setParentId( menuDO.getParentId() );
        menuBaseVO.setPath( menuDO.getPath() );
        menuBaseVO.setIcon( menuDO.getIcon() );
        menuBaseVO.setComponent( menuDO.getComponent() );
        menuBaseVO.setComponentName( menuDO.getComponentName() );
        menuBaseVO.setStatus( menuDO.getStatus() );
        menuBaseVO.setVisible( menuDO.getVisible() );
        menuBaseVO.setKeepAlive( menuDO.getKeepAlive() );
        menuBaseVO.setAlwaysShow( menuDO.getAlwaysShow() );

        return menuBaseVO;
    }

    @Override
    public MenuGetOneVO DOtoGetOneVO(MenuDO menuDO) {
        if ( menuDO == null ) {
            return null;
        }

        MenuGetOneVO menuGetOneVO = new MenuGetOneVO();

        menuGetOneVO.setName( menuDO.getName() );
        menuGetOneVO.setPermission( menuDO.getPermission() );
        menuGetOneVO.setType( menuDO.getType() );
        menuGetOneVO.setSort( menuDO.getSort() );
        menuGetOneVO.setParentId( menuDO.getParentId() );
        menuGetOneVO.setPath( menuDO.getPath() );
        menuGetOneVO.setIcon( menuDO.getIcon() );
        menuGetOneVO.setComponent( menuDO.getComponent() );
        menuGetOneVO.setComponentName( menuDO.getComponentName() );
        menuGetOneVO.setStatus( menuDO.getStatus() );
        menuGetOneVO.setVisible( menuDO.getVisible() );
        menuGetOneVO.setKeepAlive( menuDO.getKeepAlive() );
        menuGetOneVO.setAlwaysShow( menuDO.getAlwaysShow() );
        menuGetOneVO.setId( menuDO.getId() );

        return menuGetOneVO;
    }
}
