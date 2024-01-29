package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
@Schema(description = "角色绑定菜单VO")
public class RoleBindedMenuVO {
    @Schema(description = "自增id")
    private Long id;
    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "菜单权限的标识")
    private String permission;
    @Schema(description = "父级菜单id")
    private Long parentId;
}
