package com.ocean.model.vo;

import lombok.Data;

/**
 * @author ltx
 */
@Data
public class UserCreateVO {
    /**
     * 用户名称
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
