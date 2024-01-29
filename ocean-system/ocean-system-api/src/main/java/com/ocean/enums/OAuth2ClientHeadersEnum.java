package com.ocean.enums;

/**
 * @author ltx
 */
public enum OAuth2ClientHeadersEnum {
    CLIENT_ID("client_id","客户端Id"),
    SECRET("secret","客户端秘钥");
    public String header;
    public String description;
    private OAuth2ClientHeadersEnum(String header,String description){
        this.header = header;
        this.description = description;
    }
}
