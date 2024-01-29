package com.ocean.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocean.core.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ltx
 */
@Data
@TableName("system_role_menu")
@EqualsAndHashCode(callSuper = true)
public class SystemRoleMenuDO extends BaseDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;

}
