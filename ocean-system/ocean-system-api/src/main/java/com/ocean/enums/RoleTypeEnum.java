package com.ocean.enums;

/**
 * @author ltx
 */
public enum RoleTypeEnum {
    SYSTEM_INNER_ROLE(0,"系统内置角色(不可删除)"),
    CUSTOM_ROLE(1,"用户自定义角色");
    public Integer type;
    public String description;
    private RoleTypeEnum(int type,String description){
        this.type = type;
        this.description = description;
    }
}
