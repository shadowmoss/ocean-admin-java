package com.ocean.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ocean.core.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ltx
 */
@Data
@TableName("system_oauth2_refresh_token")
@EqualsAndHashCode(callSuper = true)
public class SystemOAuth2RefreshTokenDO extends BaseDO {
    /**
     * 自增id
     */
    @TableId(type= IdType.AUTO)
    private Long id;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;
    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;
}
