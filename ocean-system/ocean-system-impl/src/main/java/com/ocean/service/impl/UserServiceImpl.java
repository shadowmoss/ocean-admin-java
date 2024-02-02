package com.ocean.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.restful.RestfulPageResult;
import com.ocean.common.restful.RestfulResponse;
import com.ocean.convert.AdminUserConvert;
import com.ocean.core.utils.SecurityUtil;
import com.ocean.enums.OceanSystemDefaultClientEnum;
import com.ocean.enums.OceanSystemResCode;
import com.ocean.common.exceptions.AdminUserNotExistException;
import com.ocean.mapper.UserMapper;
import com.ocean.model.entity.SystemOAuth2AccessTokenDO;
import com.ocean.model.vo.*;
import com.ocean.model.vo.auth.AuthenticatedUserVO;
import com.ocean.service.UserService;
import com.ocean.model.dto.UserLoginDTO;
import com.ocean.model.entity.AdminUserDO;
import com.ocean.service.oauth2.OAuth2TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ltx
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private OAuth2TokenService oAuth2TokenService;

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserMapper userMapper;
    @Override
    public RestfulResponse<UserLoginResVO> login(UserLoginDTO userLoginDTO) {
        LambdaQueryWrapper<AdminUserDO> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(AdminUserDO::getUsername,userLoginDTO.getUsername());
        AdminUserDO adminUserDO = userMapper.selectOne(userLambdaQueryWrapper);
        boolean matches = passwordEncoder.matches(userLoginDTO.getPassword(),adminUserDO.getPassword());
        if(!matches){
            return RestfulResponse.fail(OceanSystemResCode.LOGIN_ERROR.description,OceanSystemResCode.LOGIN_ERROR.code,new UserLoginResVO());
        }
        SystemOAuth2AccessTokenDO systemOAuth2AccessTokenDO =  oAuth2TokenService.createAccessToken(adminUserDO, OceanSystemDefaultClientEnum.DEFAULT_CLIENT.code);
        UserLoginResVO res = UserLoginResVO.builder().userId(adminUserDO.getId()).accessToken(systemOAuth2AccessTokenDO.getAccessToken()).refreshToken(systemOAuth2AccessTokenDO.getRefreshToken()).expiresTime(systemOAuth2AccessTokenDO.getExpiresTime()).build();
        return RestfulResponse.ok(OceanSystemResCode.LOGIN_SUCCESS.description,OceanSystemResCode.LOGIN_SUCCESS.code,res);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(UserCreateVO userCreateVO) {
        AdminUserDO adminUserDO = new AdminUserDO();
        adminUserDO.setUsername(userCreateVO.getUsername());
        String newPassword = passwordEncoder.encode(userCreateVO.getPassword());
        adminUserDO.setPassword(newPassword);
        adminUserDO.setNickname(userCreateVO.getNickname());
        adminUserDO.setEmail(userCreateVO.getEmail());
        adminUserDO.setTelephone(userCreateVO.getTelephone());
        adminUserDO.setRemark(userCreateVO.getRemark());
        userMapper.insert(adminUserDO);
        return adminUserDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(UserUpdateVO userUpdateVO) {
        AdminUserDO adminUserDO = new AdminUserDO();
        adminUserDO.setId(userUpdateVO.getId());
        adminUserDO.setUsername(userUpdateVO.getUsername());
        adminUserDO.setPassword(userUpdateVO.getPassword());
        adminUserDO.setNickname(userUpdateVO.getNickname());
        adminUserDO.setEmail(userUpdateVO.getEmail());
        adminUserDO.setTelephone(userUpdateVO.getTelephone());
        adminUserDO.setRemark(userUpdateVO.getRemark());
        userMapper.updateById(adminUserDO);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUser(Long id) {
        int delete = userMapper.deleteById(id);
        return true;
    }

    @Override
    public RestfulPageResult<AdminUserPageResVO> selectUserPage(AdminUserPageVO adminUserPageVO) {
        IPage<AdminUserDO> pageObj = new Page<>(adminUserPageVO.getCurrentPageNo(),adminUserPageVO.getPageSize());
        LambdaQueryWrapper<AdminUserDO> adminUserDOLambdaQueryWrapper =new LambdaQueryWrapper<>();
        if(!Objects.isNull(adminUserPageVO.getUsername())){
            adminUserDOLambdaQueryWrapper.like(AdminUserDO::getUsername, adminUserPageVO.getUsername());
        }
        if(!Objects.isNull(adminUserPageVO.getMobile())){
            adminUserDOLambdaQueryWrapper.like(AdminUserDO::getTelephone, adminUserPageVO.getMobile());
        }
        if(!Objects.isNull(adminUserPageVO.getStartTime()) && !Objects.isNull(adminUserPageVO.getEndTime())){
            adminUserDOLambdaQueryWrapper.between(AdminUserDO::getCreateTime,adminUserPageVO.getStartTime(),adminUserPageVO.getEndTime());
        }
        IPage<AdminUserDO> adminUserDOIPage = userMapper.selectPage(pageObj,adminUserDOLambdaQueryWrapper);
        RestfulPageResult<AdminUserPageResVO> page = new RestfulPageResult<>();
        page.setCurrentPageNo(adminUserDOIPage.getCurrent());
        page.setTotal(adminUserDOIPage.getTotal());
        page.setSize(adminUserDOIPage.getSize());
        List<AdminUserPageResVO> adminUserPageResVOS = new ArrayList<>();
        for(AdminUserDO item:adminUserDOIPage.getRecords()){
            AdminUserPageResVO vo = AdminUserConvert.INSTANCE.convert(item);
            adminUserPageResVOS.add(vo);
        }
        page.setRecords(adminUserPageResVOS);
        return page;
    }

    @Override
    public AdminUserVO getOneUser(Long id) {
        LambdaQueryWrapper<AdminUserDO> adminUserDOQueryWrapper = new LambdaQueryWrapper<>();
        adminUserDOQueryWrapper.eq(AdminUserDO::getId,id);
        AdminUserDO adminUserDO = userMapper.selectOne(adminUserDOQueryWrapper);
        AdminUserVO vo = AdminUserConvert.INSTANCE.DOtoAdminUserVO(adminUserDO);
        return vo;
    }

    @Override
    public AuthenticatedUserVO getAuthenticatedUser(Long authenticatedUserId) {
        LambdaQueryWrapper<AdminUserDO> adminUserDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminUserDOLambdaQueryWrapper.eq(AdminUserDO::getId,authenticatedUserId);

        AdminUserDO adminUserDO = userMapper.selectOne(adminUserDOLambdaQueryWrapper);
        AuthenticatedUserVO vo =AdminUserConvert.INSTANCE.DOtoAuthenticatedUserVO(adminUserDO);
        return vo;
    }

    @Override
    public RestfulResponse<String> logout() {
        String accessToken = SecurityUtil.getAccessToken();
        Boolean flag = oAuth2TokenService.logOut(accessToken);
        if(flag){
            return RestfulResponse.ok(OceanSystemResCode.LOGOUT_SUCCESS.description,OceanSystemResCode.LOGOUT_SUCCESS.code,OceanSystemResCode.LOGOUT_SUCCESS.description);
        }else{
            return RestfulResponse.fail(OceanSystemResCode.LOGOUT_ERROR.description,OceanSystemResCode.LOGOUT_ERROR.code,OceanSystemResCode.LOGOUT_ERROR.description);
        }
    }

    @Override
    public AdminUserDO authenticateUser(String username, String password) {
        LambdaQueryWrapper<AdminUserDO> adminUserDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminUserDOLambdaQueryWrapper.eq(AdminUserDO::getUsername,username);
        AdminUserDO adminUserDO = userMapper.selectOne(adminUserDOLambdaQueryWrapper);
        if(adminUserDO==null){
            throw new AdminUserNotExistException("当前管理系统用户不存在");
        }
        if(passwordEncoder.matches(password,adminUserDO.getPassword())){
            return adminUserDO;
        }
        return null;
    }

    @Override
    public RestfulResponse<String> register(UserRegisterVO userRegisterVO) {
        UserCreateVO vo =  AdminUserConvert.INSTANCE.RegisterTOCreate(userRegisterVO);
        Long userId = createUser(vo);
        if(userId>=0){
            return RestfulResponse.ok(OceanSystemResCode.REQUEST_SUCCESS.description,OceanSystemResCode.REQUEST_SUCCESS.code,OceanSystemResCode.REQUEST_SUCCESS.description);
        }
        return RestfulResponse.fail(OceanSystemResCode.REGISTER_ERROR.description,OceanSystemResCode.REGISTER_ERROR.code,OceanSystemResCode.REGISTER_ERROR.description);
    }
}
