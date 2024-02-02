package com.ocean.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRegisterVO {
    @Schema(description = "用户名称")
    private String username;
    @Schema(description = "用户密码")
    private String password;
}
