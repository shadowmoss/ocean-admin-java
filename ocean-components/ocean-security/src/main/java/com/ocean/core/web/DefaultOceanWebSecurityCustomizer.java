package com.ocean.core.web;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/**
 * 配置不需要经过SpringSecurity过滤器链的Urls
 * WebSecurity是FilterChainProxy的构建器,使用ignoring方法配置其直接忽略的Url请求，这些请求不会经过SpringSecurity过滤器链，
 * 但还是会经过SpringMVC的过滤器。
 * @author ltx
 */
public class DefaultOceanWebSecurityCustomizer implements WebSecurityCustomizer {
    @Override
    public void customize(WebSecurity web) {
        web.ignoring().antMatchers(
                "/resources/**");
    }
}
