package com.ocean.controller.admin.auth;

import com.ocean.common.restful.RestfulResponse;
import com.ocean.core.utils.SecurityUtil;
import com.ocean.enums.OceanSystemResCode;
import com.ocean.enums.RoleCodeEnum;
import com.ocean.mapper.permission.SystemRoleMenuMapper;
import com.ocean.model.entity.MenuDO;
import com.ocean.model.vo.auth.AuthenticatedUserVO;
import com.ocean.model.vo.auth.AuthenticatiedUserInfoVO;
import com.ocean.model.vo.permission.AuthorityMenuVO;
import com.ocean.model.vo.permission.UserBindedRoleVO;
import com.ocean.service.UserService;
import com.ocean.service.permission.MenuService;
import com.ocean.service.permission.PermissionService;
import com.ocean.utils.AuthorityUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ltx
 */
@RestController
@RequestMapping("/auth")
public class AuthorityController {
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    @Resource
    private MenuService menuService;
    @GetMapping("/get/authenticatied/info")
    @Operation(summary = "获取当前认证用户")
    public RestfulResponse<AuthenticatiedUserInfoVO> getAuthenticatiedInfo(){
        AuthenticatiedUserInfoVO result = new AuthenticatiedUserInfoVO();

        Long authenticatedUserId = SecurityUtil.getAuthenticatedUserId();
        // 获取当前用户信息
        AuthenticatedUserVO vo = userService.getAuthenticatedUser(authenticatedUserId);

        List<UserBindedRoleVO> userBindedRoleVOS = permissionService.listUserBindedRole(authenticatedUserId);

        List<Long> roleIds = userBindedRoleVOS.stream().map(UserBindedRoleVO::getId).collect(Collectors.toList());
        // 绑定的菜单
        List<MenuDO> menuDOS = permissionService.getAllPermissions(roleIds);
        // 绑定的所有菜单上的permissions
        List<String> permissions = menuDOS.stream().filter(item->item.getPermission()!=null&&!item.getPermission().equals("")).map(MenuDO::getPermission).collect(Collectors.toList());

        // 判断当前用户的角色中是否含有超级管理员角色
        // 若存在则默认拥有所有权限
        for(UserBindedRoleVO item : userBindedRoleVOS){
            if(item.getCode().equals(RoleCodeEnum.SUPER_ADMIN.code)){
                permissions.add("*:*:*");
            }
        }

        // 绑定的所有角色Code
        List<String> roles = userBindedRoleVOS.stream().map(UserBindedRoleVO::getCode).collect(Collectors.toList());

        result.setUser(vo);
        result.setUserPermissions(permissions);
        result.setUserRoles(roles);
        return RestfulResponse.ok(OceanSystemResCode.AUTHENTICATION_USER_PERMISSION.description,OceanSystemResCode.AUTHENTICATION_USER_PERMISSION.code,result);
    }
    @GetMapping("/authority/menu")
    @Operation(summary = "获取当前用户所需的菜单列表")
    public RestfulResponse<List<AuthorityMenuVO>> getAuthorityMenus(){
        Long authenticatedUserId = SecurityUtil.getAuthenticatedUserId();
        // 获取当前用户的角色id
        List<UserBindedRoleVO> userBindedRoleVOS = permissionService.listUserBindedRole(authenticatedUserId);
        UserBindedRoleVO superAdmin = userBindedRoleVOS.stream().filter(item -> item.getCode().equals(RoleCodeEnum.SUPER_ADMIN.code)).findFirst().orElse(null);
        List<MenuDO> menuDOS = null;
        // 说明此时用户拥有超级管理员。

        if(superAdmin!=null){
            menuDOS = permissionService.getAllMenus();
        }else{
            List<Long> roleIds = userBindedRoleVOS.stream().map(UserBindedRoleVO::getId).collect(Collectors.toList());
            // 查询当前用户所授权的所有菜单信息
            menuDOS = permissionService.getAuthorityMenusByRoles(roleIds);
        }
        // 将查询到的菜单列表转换为树形结构。
        List<AuthorityMenuVO> authorityMenuVOS = AuthorityUtils.generateTree(menuDOS);
        return RestfulResponse.ok("查询成功",authorityMenuVOS);
    }

}
