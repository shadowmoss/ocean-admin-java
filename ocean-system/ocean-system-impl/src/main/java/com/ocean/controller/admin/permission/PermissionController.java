package com.ocean.controller.admin.permission;

import com.ocean.common.restful.RestfulResponse;
import com.ocean.model.vo.permission.RoleBindMenuVO;
import com.ocean.model.vo.permission.RoleBindedMenuVO;
import com.ocean.model.vo.permission.UserBindRoleVO;
import com.ocean.model.vo.permission.UserBindedRoleVO;
import com.ocean.service.permission.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ltx
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;
    @PostMapping("/user/bind/role")
    @Operation(summary = "用户角色绑定")
    public RestfulResponse<Boolean> userBindRole(@RequestBody UserBindRoleVO userBindRoleVO){
        Boolean flag = permissionService.userBindRole(userBindRoleVO);
        return RestfulResponse.ok("绑定成功",flag);
    }

    @PostMapping("/role/bind/menu")
    @Operation(summary = "角色绑定菜单")
    public RestfulResponse<Boolean> roleBindMenu(@RequestBody RoleBindMenuVO roleBindMenuVO){
        Boolean flag = permissionService.roleBindMenu(roleBindMenuVO);
        return RestfulResponse.ok("绑定成功",flag);
    }
    @GetMapping("/list/user/binded/role")
    @Operation(summary = "获取当前用户已绑定的角色")
    public RestfulResponse<List<UserBindedRoleVO>> userBindedRole(Long id){
        List<UserBindedRoleVO> vos = permissionService.listUserBindedRole(id);
        return RestfulResponse.ok("查询完成",vos);
    }
    @GetMapping("/list/role/binded/menu")
    @Operation(summary = "获取当前角色已绑定的菜单")
    public RestfulResponse<List<RoleBindedMenuVO>> roleBindedMenu(Long id){
        List<RoleBindedMenuVO> roleBindedMenuVOS = permissionService.listRoleBindedMenu(id);
        return RestfulResponse.ok("查询完成",roleBindedMenuVOS);
    }
}
