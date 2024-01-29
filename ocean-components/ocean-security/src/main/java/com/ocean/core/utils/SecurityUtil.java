package com.ocean.core.utils;

import com.ocean.core.authentication.OceanAuthentication;
import com.ocean.core.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ltx
 */
public class SecurityUtil {
    public static Long getAuthenticatedUserId(){
        SecurityContext securityContext = getSecurityContext();
        OceanAuthentication authentication = (OceanAuthentication)securityContext.getAuthentication();
        LoginUser user = authentication.getUser();
        return user.getId();
    }

    public static SecurityContext getSecurityContext(){
        return SecurityContextHolder.getContext();
    }

    public static String getAccessToken(){
        SecurityContext securityContext = getSecurityContext();
        OceanAuthentication authentication = (OceanAuthentication)securityContext.getAuthentication();
        LoginUser user = authentication.getUser();
        return user.getAccessToken();
    }
}
