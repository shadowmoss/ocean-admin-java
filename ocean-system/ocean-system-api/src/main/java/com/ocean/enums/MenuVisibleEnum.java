package com.ocean.enums;

/**
 * @author ltx
 */
public enum MenuVisibleEnum {
    VISIBLE(0,"可见"),
    UNVISIBLE(1,"隐藏");
    public int code;
    public String description;
    private MenuVisibleEnum(int code,String description){
        code = code;
        description = description;
    }
}
