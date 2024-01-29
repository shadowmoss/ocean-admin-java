package com.ocean.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
public class UserLoginDTO {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
}
