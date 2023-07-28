package com.ocean.controller;

import com.ocean.common.restful.RestfulResponse;
import com.ocean.service.UserService;
import com.ocean.model.dto.UserLoginDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ltx
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping( "/login")
    public RestfulResponse<String> login(@RequestBody UserLoginDTO userLoginDTO){
       return userService.login(userLoginDTO);
    }
}
