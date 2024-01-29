package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author ltx
 */
@Data
@Schema(description = "用户绑定角色")
public class UserBindRoleVO {
    @Schema(description = "用户Id")
    private Long userId;
    @Schema(description = "角色ids")
    private List<Long> roleIds;
}
