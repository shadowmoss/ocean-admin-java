package com.ocean.model.vo.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ltx
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDeleteResultVO {
    /**
     * 删除角色时返回的信息
     */
    private String msg;
    /**
     * 删除角色成功与否
     */
    private Boolean flag;
}
