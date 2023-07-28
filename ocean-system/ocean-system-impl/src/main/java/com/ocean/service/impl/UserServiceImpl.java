package com.ocean.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ocean.common.restful.RestfulResponse;
import com.ocean.mapper.UserMapper;
import com.ocean.service.UserService;
import com.ocean.model.dto.UserLoginDTO;
import com.ocean.model.entity.User;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * @author ltx
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public RestfulResponse<String> login(UserLoginDTO userLoginDTO) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,userLoginDTO.getUsername());
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if(!user.getPassword().equals(userLoginDTO.getPassword())){
            return RestfulResponse.fail("登录失败","登录失败");
        }
        return RestfulResponse.ok("登录成功","登录成功");
    }
}
