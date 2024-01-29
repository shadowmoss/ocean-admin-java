package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
@Schema(description = "查询当前所有可用角色")
public class RoleListVO {
    @Schema(description = "自增id")
    private Long id;
    @Schema(description = "角色名称")
    private String name;
    @Schema(description = "角色唯一标识代码")
    private String code;
    @Schema(description = "状态,0:启用.1:停用")
    private Integer status;
    @Schema(description = "角色类型,0:系统内置角色,1:用户自定义角色")
    private Integer roleType;
}
