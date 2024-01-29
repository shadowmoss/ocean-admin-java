package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色更新VO")
public class RoleUpdateVO extends RoleCreateVO{
    @Schema(description = "角色id")
    private Long id;
}
