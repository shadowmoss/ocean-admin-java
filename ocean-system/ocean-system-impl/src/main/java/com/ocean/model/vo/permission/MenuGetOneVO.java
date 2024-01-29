package com.ocean.model.vo.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ltx
 */
@Schema(description = "查询一个菜单信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuGetOneVO extends MenuBaseVO{
    @Schema(description = "单个菜单信息")
    private Long id;
}
