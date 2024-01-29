package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author ltx
 */
@Data
@Schema(description = "角色绑定菜单VO")
public class RoleBindMenuVO {
    @Schema(description = "角色id")
    private Long roleId;
    @Schema(description = "菜单ids")
    private List<Long> menuIds;
}
