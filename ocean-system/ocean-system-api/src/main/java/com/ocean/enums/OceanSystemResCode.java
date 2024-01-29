package com.ocean.enums;

/**
 * @author ltx
 */
public enum OceanSystemResCode {
    LOGIN_ERROR(1000001,"登录错误"),
    LOGIN_SUCCESS(1000002,"登录成功"),
    AUTHENTICATION_USER_PERMISSION(1000003,"认证用户权限返回成功"),
    LOGOUT_SUCCESS(1000004,"用户登出成功"),
    LOGOUT_ERROR(1000005,"用户登出失败"),

    OAUTH2_CLIENT_CREATE_FAILURE(2000001,"OAuth2客户端创建失败"),

    OAUTH2_CLIENT_UPDATE_FAILURE(2000002,"OAuth2客户端更新失败"),

    OAUTH2_CLIENT_DELETE_FAILURE(2000003,"OAuth2客户端删除失败")
    ;

    public int code;
    public String description;
    private OceanSystemResCode(int code,String description){
        this.code = code;
        this.description = description;
    }
}
