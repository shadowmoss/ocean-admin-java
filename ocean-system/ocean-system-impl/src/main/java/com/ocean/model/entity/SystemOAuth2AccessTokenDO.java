package com.ocean.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocean.core.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_oauth2_access_token")
public class SystemOAuth2AccessTokenDO extends BaseDO {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;
}
