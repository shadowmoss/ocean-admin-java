package com.ocean.enums;

/**
 * @author ltx
 */
public enum RoleStatusEnum {
    ROLE_ENABLE(0,"启用角色"),
    ROLE_DISABLE(1,"停用角色");
    public int status;
    public String description;
    private RoleStatusEnum(int status,String description){
        this.status = status;
        this.description = description;
    }
}
