package com.ocean.common.exceptions;

/**
 * @author ltx
 */
public class AdminUserNotExistException extends RuntimeException{
    public AdminUserNotExistException(String message) {
        super(message);
    }
}
