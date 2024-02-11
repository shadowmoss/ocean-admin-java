package com.ocean.common.enums;

/**
 * @author ltx
 */
public enum CommonResCode {
    REQ_SUCCESS(6000001,"请求成功"),
    REQ_FAILURE(6000002,"请求失败"),

    ACCESS_TOKEN_EXPIRE(6000003,"访问令牌过期"),

    ACCESS_TOKEN_DELETED(6000004,"访问令牌已被删除"),



    ADMIN_USER_NOT_EXIST(6000005,"认证用户不存在"),
    REFRESH_TOKEN_EXPIRE(6000006,"刷新令牌已过期"),
    REFRESH_TOKEN_DELETED(6000007,"刷新令牌已被删除"),
    ;
    public int code;
    public String description;
    private CommonResCode(int code,String description){
        this.code = code;
        this.description = description;
    }
}
