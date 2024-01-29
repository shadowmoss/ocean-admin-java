package com.ocean.model.vo.permission;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
public class MenuBaseVO {

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限标识,只有菜单类型为按钮时,才有这个值。")
    private String permission;

    @Schema(description = "菜单类型,1:菜单目录,2:菜单,3:菜单按钮")
    private Integer type;

    @Schema(description = "菜单排序")
    private Integer sort;

    @Schema(description = "父级菜单id")
    private Long parentId;

    @Schema(description = "菜单路由,只有当菜单类型为目录或者菜单时才需要传递")
    private String path;

    @Schema(description = "菜单图标,当菜单类型为菜单时,才需要传递")
    private String icon;

    @Schema(description = "菜单组件路径,当菜单类型为菜单时,才需要传递")
    private String component;

    @Schema(description = "组件名称,当菜单需要被keep-alive组件缓存时,必须具有该名称。与下述keepAlive字段配合")
    private String componentName;

    @Schema(description = "菜单状态,0:表示菜单启用,1:表示菜单未启用")
    private Integer status;

    @Schema(description = "菜单是否可见,0:表示左侧边栏可见,1:表示左侧边栏不可见,但是路由存在")
    private Integer visible;

    @Schema(description = "表示当前菜单是否可以被keep-alive组件缓存,0:表示可被缓存,1:表示不可被缓存")
    private Integer keepAlive;

    @Schema(description = "当该目录只有一个子菜单时,是否显示展示自身,0:表示总是展示,1:表示总是不展示。")
    private Integer alwaysShow;
}
