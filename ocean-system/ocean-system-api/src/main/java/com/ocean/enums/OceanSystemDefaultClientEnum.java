package com.ocean.enums;

/**
 * @author ltx
 */
public enum OceanSystemDefaultClientEnum {
    DEFAULT_CLIENT("default","默认客户端")
    ;
    public String code;
    public String description;
    private OceanSystemDefaultClientEnum(String code,String description){
        this.code = code;
        this.description = description;
    }
}
