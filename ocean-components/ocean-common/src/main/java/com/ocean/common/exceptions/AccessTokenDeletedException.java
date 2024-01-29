package com.ocean.common.exceptions;

/**
 * 访问令牌以被删除异常
 * @author ltx
 */
public class AccessTokenDeletedException extends RuntimeException{
    public AccessTokenDeletedException(String message) {
        super(message);
    }
}
