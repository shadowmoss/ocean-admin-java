package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ltx
 */
@Data
@Schema(description = "角色创建VO")
public class RoleCreateVO {
    @Schema(description = "角色名称")
    private String name;
    @Schema(description = "角色标识符,(用于参与业务逻辑时使用)")
    private String code;
    @Schema(description = "角色显示顺序")
    private Integer roleOrder;
    @Schema(description = "角色状态,0:表示启用,1:表示未启用")
    private Integer status;
    @Schema(description = "角色备注信息")
    private String remark;
}
