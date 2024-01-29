package com.ocean.enums;

/**
 * @author ltx
 */
public enum OAuth2GrantType {
    AUTHORITY_CODE("authority_code","授权码模式"),
    PASSWORD("password","密码模式"),
    REFRESH("refresh","刷新令牌");

    private String code;
    private String description;
    OAuth2GrantType(String code,String description){
        this.code = code;
        this.description = description;
    }

    public static OAuth2GrantType getGrantType(String code){
        for(OAuth2GrantType item :OAuth2GrantType.values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        return null;
    }
}
