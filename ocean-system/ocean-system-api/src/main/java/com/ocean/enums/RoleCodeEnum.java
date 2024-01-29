package com.ocean.enums;

/**
 * @author ltx
 */
public enum RoleCodeEnum {
    SUPER_ADMIN("super_admin","超级管理员");
    public String code;
    public String description;
    private RoleCodeEnum(String code,String description){
        this.code = code;
        this.description = description;
    }
}
