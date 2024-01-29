package com.ocean.mapper.permission;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.enums.MenuType;
import com.ocean.model.entity.MenuDO;
import com.ocean.model.entity.SystemRoleMenuDO;
import com.ocean.model.vo.permission.RoleBindedMenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ltx
 */
@Mapper
public interface SystemRoleMenuMapper extends BaseMapper<SystemRoleMenuDO> {
    List<RoleBindedMenuVO> listRoleBindedMenus(@Param("id") Long id);

    List<MenuDO> getAllPermissions(@Param("roleIds") List<Long> roleIds);

    SystemRoleMenuDO selectExist(@Param("menuId") Long menuId,@Param("roleId") Long roleId);

    void updateDeleted(@Param("oldData") SystemRoleMenuDO oldData);

    List<MenuDO> getAllMenusByRoles(@Param("menuTypes") List<Integer> menuTypes,@Param("roleIds") List<Long> roleIds);
}
