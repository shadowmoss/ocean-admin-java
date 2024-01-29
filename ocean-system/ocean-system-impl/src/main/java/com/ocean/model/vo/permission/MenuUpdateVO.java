package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuUpdateVO extends MenuBaseVO{
    @Schema(description = "菜单id")
    private Long id;
}
