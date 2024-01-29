package com.ocean.model.vo.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
@Schema(description = "已认证用户信息")
public class AuthenticatedUserVO {
    @Schema(description = "已认证用户id")
    private Long userId;
    @Schema(description = "已认证用户名称")
    private String username;
    @Schema(description = "已认证用户头像")
    private String avatar;
    @Schema(description = "已认证用户昵称")
    private String nickname;
}
