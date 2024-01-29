package com.ocean.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ocean.core.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_oauth2_client",autoResultMap = true)
public class SystemOAuth2ClientDO extends BaseDO {
    /**
     * 自增id
     */
    @TableId(type=IdType.AUTO)
    private Long id;
    /**
     * 客户端id
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
     * 应用图标
     */
    private String logo;

    /**
     * 描述
     */
    private String description;

    /**
     * 客户端启用状态,0:启用,1:未启用
     */
    private Integer status;

    /**
     * 访问令牌有效期
     */
    private Long accessTokenValiditySeconds;

    /**
     * 刷新令牌有效期
     */
    private Long refreshTokenValiditySeconds;

    /**
     * 可用重定向URI
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> redirectUris;

    /**
     * 授权类型
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authorizedGrantTypes;

    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;

    /**
     * 自动授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> autoApproveScopes;

    /**
     * 权限
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authorities;

    /**
     * 资源
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> resourceIds;

    /**
     * 附加信息,JSON格式
     */
    private String additionalInformation;
}
