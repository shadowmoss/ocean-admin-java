package com.ocean.enums;

/**
 * @author ltx
 */
public enum MenuType {
    MENU_DIRECTORY(1,"菜单目录"),
    MENU_PAGE(2,"菜单页面"),
    MENU_BUTTON(3,"菜单按钮");
    public int type;
    public String description;
    private MenuType(int type,String description){
        this.type = type;
        this.description = description;
    }
}
