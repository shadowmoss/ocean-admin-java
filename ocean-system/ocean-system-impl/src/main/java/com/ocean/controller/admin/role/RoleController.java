package com.ocean.controller.admin.role;

import com.ocean.common.restful.RestfulPageResult;
import com.ocean.common.restful.RestfulResponse;
import com.ocean.model.vo.permission.*;
import com.ocean.service.permission.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ltx
 */
@Tag(name = "后台角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @PostMapping("/create")
    @Operation(summary = "创建角色")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:role:insert')")
    public RestfulResponse<Long> createRole(@RequestBody RoleCreateVO roleCreateVO){
        Long id =  roleService.createRole(roleCreateVO);
        return RestfulResponse.ok("创建成功",id);
    }
    @PutMapping("/update")
    @Operation(summary = "更新角色")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:role:update')")
    public RestfulResponse<Boolean> updateRole(@RequestBody RoleUpdateVO roleUpdateVO){
        Boolean flag = roleService.updateRole(roleUpdateVO);
        return RestfulResponse.ok("更新成功",true);
    }
    @DeleteMapping("/delete")
    @Operation(summary = "删除角色")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:role:delete')")
    public RestfulResponse<Boolean> deleteRole(@RequestParam("id") Long id){
        RoleDeleteResultVO vo =  roleService.deleteRole(id);
        if(vo.getFlag()){
            return RestfulResponse.ok(vo.getMsg(),vo.getFlag());
        }else{
            return RestfulResponse.fail(vo.getMsg(),vo.getFlag());
        }
    }
    @GetMapping("/page")
    @Operation(summary = "角色分页")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:role:query')")
    public RestfulResponse<RestfulPageResult<RolePageVO>> pageRole( RolePageReqVO rolePageReqVO){
        RestfulPageResult<RolePageVO> rolePageVORestfulPageResult = roleService.pageRole(rolePageReqVO);
        return RestfulResponse.ok("查询分页",rolePageVORestfulPageResult);
    }

    @GetMapping("/getOne")
    @Operation(summary = "根据角色id查询角色信息")
    public RestfulResponse<RoleGetOneVO> getOneRole(Long id){
        RoleGetOneVO vo = roleService.getOneRole(id);
        return RestfulResponse.ok("查询成功",vo);
    }
    @GetMapping("/list")
    @Operation(summary = "列出当前所有可用角色")
    public RestfulResponse<List<RoleListVO>> listRole(){
        List<RoleListVO> roleListVOS = roleService.listEnableRole();
        return RestfulResponse.ok("查询成功",roleListVOS);
    }
}
