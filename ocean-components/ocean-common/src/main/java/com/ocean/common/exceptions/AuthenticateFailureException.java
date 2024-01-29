package com.ocean.common.exceptions;

/**
 * @author ltx
 */
public class AuthenticateFailureException extends RuntimeException{
    public AuthenticateFailureException(String message) {
        super(message);
    }
}
