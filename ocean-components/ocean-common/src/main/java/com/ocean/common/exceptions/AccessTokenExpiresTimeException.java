package com.ocean.common.exceptions;

/**
 * 访问令牌过期异常
 * @author ltx
 */
public class AccessTokenExpiresTimeException extends RuntimeException{
    public AccessTokenExpiresTimeException(String message) {
        super(message);
    }
}
