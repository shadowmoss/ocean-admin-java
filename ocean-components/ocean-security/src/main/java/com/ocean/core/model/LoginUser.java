package com.ocean.core.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author ltx
 */
@Data
@Builder
public class LoginUser {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户的名称
     */
    private String username;
    /**
     * 用户的访问令牌
     */
    private String accessToken;
}
