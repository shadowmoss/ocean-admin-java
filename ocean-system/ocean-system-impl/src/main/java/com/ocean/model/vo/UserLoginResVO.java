package com.ocean.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author ltx
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResVO {
    @Schema(description = "用户id")
    private Long userId;
    @Schema(description = "用户访问令牌")
    private String accessToken;
    @Schema(description = "刷新令牌")
    private String refreshToken;
    @Schema(description = "过期时间")
    private LocalDateTime expiresTime;
}
