package com.ocean.config;

import api.oauth2.OAuth2Api;
import api.permission.PermissionApi;
import com.ocean.core.authority.DefaultOceanAuthoritySecurityService;
import com.ocean.core.authority.OceanAuthoritySecurityService;
import com.ocean.core.filter.OceanAuthenticationFilter;
import com.ocean.web.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ltx
 */
@Configuration
public class SercurityItemConfig {
    /**
     * 配置当前系统的密码编译器
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置当前系统进行身份认证的Filter
     * @param securityProperty
     * @param oAuth2Api
     * @return
     */
    @Bean
    public OceanAuthenticationFilter getAuthenticationFilter(SecurityProperty securityProperty, OAuth2Api oAuth2Api, GlobalExceptionHandler globalExceptionHandler){
        return new OceanAuthenticationFilter(securityProperty,oAuth2Api,globalExceptionHandler);
    }

    /**
     * 配置当前项目进行授权的工具类。
     */
    @Bean("authoritySecurityService")
    public OceanAuthoritySecurityService getAuthoritySecurityService(PermissionApi permissionApi){
        return new DefaultOceanAuthoritySecurityService(permissionApi);
    }
}
