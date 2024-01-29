package com.ocean.controller.admin.menu;

import com.ocean.common.restful.RestfulResponse;
import com.ocean.model.vo.permission.*;
import com.ocean.service.permission.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ltx
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;
    @PostMapping("/create")
    @Operation(summary = "创建菜单")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:menu:insert')")
    public RestfulResponse<Long> createMenu(@RequestBody MenuCreateVO menuCreateVO){
        Long id =  menuService.createMenu(menuCreateVO);
        return RestfulResponse.ok("创建成功",id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新菜单")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:menu:update')")
    public RestfulResponse<Boolean> updateMenu(@RequestBody MenuUpdateVO menuUpdateVO){
        Boolean flag = menuService.updateMenu(menuUpdateVO);
        if(flag){
            return RestfulResponse.ok("更新成功",flag);
        }else {
            return RestfulResponse.fail("更新失败",flag);
        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除菜单")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:menu:delete')")
    public RestfulResponse<Boolean> deleteMenu(@RequestParam Long id){
        Boolean flag = menuService.deleteMenu(id);
        if(flag){
            return RestfulResponse.ok("删除成功",flag);
        }else {
            return RestfulResponse.fail("删除失败",flag);
        }
    }
    @GetMapping("/list")
    @Operation(summary = "查询菜单树")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:menu:query')")
    public RestfulResponse<List<MenuResVO>> listMenuTree(@ParameterObject MenuListVO menuListVO){
        List<MenuResVO> menu = menuService.listMenu(menuListVO);
        return RestfulResponse.ok("查询成功",menu);
    }
    @GetMapping("/getOne")
    @Operation(summary = "查询某一个菜单")
    public RestfulResponse<MenuGetOneVO> getOneMenu(Long id){
        MenuGetOneVO vo = menuService.getOne(id);
        return RestfulResponse.ok("查询成功",vo);
    }
}
