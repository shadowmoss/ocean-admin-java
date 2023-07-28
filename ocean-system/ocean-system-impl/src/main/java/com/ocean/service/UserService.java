package com.ocean.service;

import com.ocean.common.restful.RestfulResponse;
import com.ocean.model.dto.UserLoginDTO;

/**
 * @author ltx
 */
public interface UserService {
    RestfulResponse<String> login(UserLoginDTO userLoginDTO);
}
