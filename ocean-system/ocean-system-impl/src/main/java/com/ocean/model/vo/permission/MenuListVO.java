package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
public class MenuListVO {
    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "菜单状态,0:表示菜单启用,1:表示菜单未启用")
    private Integer status;
}
