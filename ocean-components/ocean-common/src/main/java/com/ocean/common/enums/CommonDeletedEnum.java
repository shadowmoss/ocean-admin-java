package com.ocean.common.enums;

/**
 * @author ltx
 */
public enum CommonDeletedEnum {

    NOT_DELETE(0,"当前数据未进行逻辑删除"),
    DELETED(1,"当前数据已经进行逻辑删除");
    public int code;
    public String description;
    private CommonDeletedEnum(int code,String description){
        this.code = code;
        this.description = description;
    }
}
