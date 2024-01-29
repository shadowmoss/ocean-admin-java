package com.ocean.model.vo.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author ltx
 */
@Data
@Schema(description = "获取当前已认证的用户信息")
public class AuthenticatiedUserInfoVO {
    private List<String> userPermissions;
    private List<String> userRoles;
    private AuthenticatedUserVO user;
}
