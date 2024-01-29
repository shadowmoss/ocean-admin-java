package com.ocean.common.exceptions;

/**
 * @author ltx
 */
public class RefreshTokenExpireException extends RuntimeException{
    public RefreshTokenExpireException(String message) {
        super(message);
    }
}
