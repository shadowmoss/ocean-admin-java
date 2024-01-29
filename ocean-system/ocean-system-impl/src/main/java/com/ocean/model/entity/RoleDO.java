package com.ocean.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocean.core.model.BaseDO;
import lombok.*;

/**
 * @author ltx
 */
@Data
@TableName("system_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDO extends BaseDO {
    @TableId(type=IdType.AUTO)
    private Long id;

    /**
     * 角色名称:一般是中文名称
     */
    private String name;

    /**
     * 角色代码:参与到系统业务逻辑当中的值
     */
    private String code;

    /**
     * 角色显示顺序
     */
    private Integer roleOrder;

    /**
     *  角色状态:
     *  0:启用,
     *  1:停用
     */
    private Integer status;

    /**
     * 角色类型:0:表示内置角色(不可进行删除),1:表示用户自定义角色(不可进行删除)
     */
    private Integer roleType;

    /**
     * 角色备注
     */
    private String remark;
}
