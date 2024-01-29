package com.ocean.service.permission;

import com.ocean.model.entity.MenuDO;
import com.ocean.model.vo.permission.RoleBindMenuVO;
import com.ocean.model.vo.permission.RoleBindedMenuVO;
import com.ocean.model.vo.permission.UserBindRoleVO;
import com.ocean.model.vo.permission.UserBindedRoleVO;

import java.util.List;

/**
 * @author ltx
 */
public interface PermissionService {
    Boolean userBindRole(UserBindRoleVO userBindRoleVO);

    Boolean roleBindMenu(RoleBindMenuVO roleBindMenuVO);

    List<UserBindedRoleVO> listUserBindedRole(Long id);

    List<RoleBindedMenuVO> listRoleBindedMenu(Long id);

    List<MenuDO> getAllPermissions(List<Long> roleIds);

    List<MenuDO> getAuthorityMenusByRoles(List<Long> roleIds);

    boolean hasAnyPermission(Long authenticatedUserId, String... permissions);

    boolean hasAnyRole(Long authenticatedUserId, String... roles);

    List<MenuDO> getAllMenus();
}
