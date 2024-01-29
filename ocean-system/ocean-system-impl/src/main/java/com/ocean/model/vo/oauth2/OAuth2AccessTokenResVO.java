package com.ocean.model.vo.oauth2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ltx
 */
@Data
public class OAuth2AccessTokenResVO {

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "过期日期")
    private LocalDateTime expiresTime;
}
