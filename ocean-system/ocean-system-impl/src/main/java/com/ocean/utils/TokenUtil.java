package com.ocean.utils;

import java.util.UUID;

/**
 * @author ltx
 */
public class TokenUtil {
    public static String getAccessToken(){
        return UUID.randomUUID().toString();
    }
    public static String getRefreshToken() {
        return UUID.randomUUID().toString();
    }
}
