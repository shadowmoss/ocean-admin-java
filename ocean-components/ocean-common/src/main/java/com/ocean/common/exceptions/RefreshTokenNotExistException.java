package com.ocean.common.exceptions;

/**
 * 刷新令牌不存在异常
 * @author ltx
 */
public class RefreshTokenNotExistException extends RuntimeException{
    public RefreshTokenNotExistException(String message) {
        super(message);
    }
}
