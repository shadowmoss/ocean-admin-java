package com.ocean.model.vo.oauth2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
public class RequestAccessTokenVO {

    /**
     * 该字段对应,给第三方应用配置的授权类型,用于该客户端可以使用哪些授权功能。
     */
    @Schema(description = "进行访问令牌请求的请求类型:authority_code(授权码模式)、password(密码模式)、refresh(刷新令牌)")
    private String requestType;

    @Schema(description = "授权码")
    private String code;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "刷新令牌")
    private String refreshToken;
}
