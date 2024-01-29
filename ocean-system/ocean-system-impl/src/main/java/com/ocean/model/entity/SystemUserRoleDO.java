package com.ocean.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocean.core.model.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ltx
 */
@Data
@TableName("system_user_role")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SystemUserRoleDO extends BaseDO {
    @TableId(type=IdType.AUTO)
    private Long id;
    /**
     *  用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;
}
