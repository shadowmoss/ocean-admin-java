package com.ocean.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
@Schema(description = "查询单个用户")
public class AdminUserVO {
    @Schema(description = "自增id")
    private Long id;
    @Schema(description = "用户名称")
    private String username;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "邮件")
    private String email;
    @Schema(description = "电话")
    private String telephone;
    @Schema(description = "备注")
    private String remark;
}
