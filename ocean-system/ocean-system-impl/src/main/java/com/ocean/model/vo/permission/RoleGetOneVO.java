package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Schema(description = "查询某一角色")
@Data
public class RoleGetOneVO {
    @Schema(description = "自增id")
    private Long id;
    @Schema(description = "角色名称")
    private String name;
    @Schema(description = "角色标识")
    private String code;
    @Schema(description = "显示顺序")
    private Integer roleOrder;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "备注")
    private String remark;
}
