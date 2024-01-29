package com.ocean.model.vo.oauth2;

import lombok.Data;

import java.util.List;

/**
 * @author ltx
 */
@Data
public class BaseOAuth2ClientVO {
    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 客户端秘钥
     */
    private String secret;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用logo
     */
    private String logo;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 状态:0:启用,1:未启用
     */
    private Integer status;

    /**
     * 访问令牌有效期
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌有效期
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 授权类型:授权码模式、密码模式、刷新令牌、authority_code,
     */
    private List<String> authorizedGrantTypes;

    /**
     * 可用的重定向URI
     */
    private List<String> redirectUris;
}
