package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author ltx
 */
@Data
@Schema(description = "当前用户被授权的菜单")
public class AuthorityMenuVO {
    @Schema(description = "菜单id")
    private Long id;
    @Schema(description = "父菜单id")
    private Long parentId;
    @Schema(description = "菜单类型")
    private Integer type;
    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "菜单路由")
    private String path;
    @Schema(description = "组件名称")
    private String componentName;
    @Schema(description = "组件路径")
    private String component;
    @Schema(description = "菜单图标")
    private String icon;
    @Schema(description = "菜单可见性,0:可见,1:不可见")
    private Integer visible;
    @Schema(description = "菜单缓存性,0:可缓存,1:不可缓存")
    private Integer keepAlive;
    @Schema(description = "菜单是否总是展示,0:总是展示该菜单,1:当前只有在有子菜单时才展示")
    private Integer alwaysShow;
    @Schema(description = "子菜单")
    private List<AuthorityMenuVO> children;
}
