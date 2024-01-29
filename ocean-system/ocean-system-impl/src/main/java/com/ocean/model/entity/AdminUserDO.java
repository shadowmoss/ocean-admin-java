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
@TableName("user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AdminUserDO extends BaseDO {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮件
     */
    private String email;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 备注
     */
    private String remark;
}
