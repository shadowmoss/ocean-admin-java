package com.ocean.controller.admin.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ocean.common.restful.RestfulPageResult;
import com.ocean.common.restful.RestfulResponse;
import com.ocean.model.entity.AdminUserDO;
import com.ocean.model.vo.*;
import com.ocean.service.UserService;
import com.ocean.model.dto.UserLoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ltx
 */
@Tag(name="用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping( "/login")
    @Operation(summary = "用户登录")
    public RestfulResponse<UserLoginResVO> login(@RequestBody UserLoginDTO userLoginDTO){
       return userService.login(userLoginDTO);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public RestfulResponse<String> register(@RequestBody UserRegisterVO userRegisterVO){
       return userService.register(userRegisterVO);
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    public RestfulResponse<String> logout(){
        return userService.logout();
    }

    @PostMapping("/create")
    @Operation(summary = "用户创建")
    public RestfulResponse<Long> createUser(@RequestBody UserCreateVO userCreateVO){
        Long id = userService.createUser(userCreateVO);
        return RestfulResponse.ok("创建成功",id);
    }

    @PutMapping("/update")
    @Operation(summary = "用户更新")
    public RestfulResponse<Boolean> updateUser(@RequestBody UserUpdateVO userUpdateVO){
        Boolean flag = userService.updateUser(userUpdateVO);
        return RestfulResponse.ok("更新成功",flag);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "用户删除")
    public RestfulResponse<Boolean> deleteUser(@RequestParam Long id){
        Boolean flag = userService.deleteUser(id);
        return RestfulResponse.ok("删除成功",flag);
    }

    @GetMapping("/page")
    @Operation(summary = "查询用户分页")
    @PreAuthorize("@authoritySecurityService.hasPermission('system:user:query')")
    public RestfulResponse<RestfulPageResult<AdminUserPageResVO>> pageAdminUser( AdminUserPageVO adminUserPageVO){
        RestfulPageResult<AdminUserPageResVO> adminUserPageVOPage = userService.selectUserPage(adminUserPageVO);
        return RestfulResponse.ok("查询成功",adminUserPageVOPage);
    }

    @GetMapping("/getOne")
    @Operation(summary = "根据用户id查询用户")
    public RestfulResponse<AdminUserVO> getOneUser(@RequestParam Long id){
        AdminUserVO adminUserVORestfulResponse = userService.getOneUser(id);
        return RestfulResponse.ok("查询成功",adminUserVORestfulResponse);
    }

}
