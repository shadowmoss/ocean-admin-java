package com.ocean.service;

import com.ocean.common.restful.RestfulPageResult;
import com.ocean.common.restful.RestfulResponse;
import com.ocean.model.dto.UserLoginDTO;
import com.ocean.model.entity.AdminUserDO;
import com.ocean.model.vo.*;
import com.ocean.model.vo.auth.AuthenticatedUserVO;

/**
 * @author ltx
 */
public interface UserService {
    RestfulResponse<UserLoginResVO> login(UserLoginDTO userLoginDTO);

    Long createUser(UserCreateVO userCreateVO);

    Boolean updateUser(UserUpdateVO userUpdateVO);

    Boolean deleteUser(Long id);

    RestfulPageResult<AdminUserPageResVO> selectUserPage(AdminUserPageVO adminUserPageVO);

    AdminUserVO getOneUser(Long id);

    AuthenticatedUserVO getAuthenticatedUser(Long authenticatedUserId);

    RestfulResponse<String> logout();

    AdminUserDO authenticateUser(String username, String password);

    RestfulResponse<String> register(UserRegisterVO userRegisterVO);
}
