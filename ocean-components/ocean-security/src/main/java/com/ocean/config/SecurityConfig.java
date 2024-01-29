package com.ocean.config;

import api.oauth2.OAuth2Api;
import com.ocean.core.filter.OceanAuthenticationFilter;
import com.ocean.core.web.DefaultOceanWebSecurityCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;
import java.util.logging.Filter;

/**
 * @author ltx
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Resource
    private OceanAuthenticationFilter oceanAuthenticationFilter;
    @Bean("OceanWebSecurityCustomizer")
    public WebSecurityCustomizer webSecurityCustomizer(){
        return new DefaultOceanWebSecurityCustomizer();
    }
    @Bean
    public SecurityFilterChain getHttpSecurity(HttpSecurity httpSecurity) throws Exception{
        // 这里的配置操作相当于在创建一个SpringSecurity管理的过滤器链
        // 我们可以配置哪些请求可以被本过滤器链进行操作.
        // 表示开启权限配置
        httpSecurity
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 声明创建一个AuthorityFilter
                .authorizeHttpRequests()
                // 配置需要进行验证的RequestMatcher
                .antMatchers(HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/*/api-docs"
                        ).
                permitAll()
                .antMatchers(
                        "/user/login",
                        "/oauth2/token/get"
                ).permitAll()
                .anyRequest()
                // 表示任何请求都需要进行认证才能访问
                .authenticated();
        httpSecurity.getConfigurer(SecurityContextConfigurer.class).requireExplicitSave(true);
        httpSecurity.addFilterAfter(oceanAuthenticationFilter, SecurityContextHolderFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService noneUserDetailService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        };
    }
    public CorsConfigurationSource corsConfigurationSource() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(false);

        configuration.setAllowedOrigins(Collections.singletonList("*"));

        configuration.setAllowedMethods(Collections.singletonList("*"));

        configuration.setAllowedHeaders(Collections.singletonList("*"));

        configuration.setMaxAge(Duration.ofHours(1));

        source.registerCorsConfiguration("/**", configuration);

        return source;

    }
}
