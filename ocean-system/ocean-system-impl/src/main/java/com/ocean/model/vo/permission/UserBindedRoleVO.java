package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
@Schema(description = "当前用户已绑定的角色")
public class UserBindedRoleVO {
    @Schema(description = "自增Id")
    private Long id;
    @Schema(description = "角色名称")
    private String name;
    @Schema(description = "角色标识")
    private String code;
}
