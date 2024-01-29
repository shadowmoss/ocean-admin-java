package com.ocean.core.filter;

import api.oauth2.OAuth2Api;
import api.oauth2.dto.OAuth2AuthenticationAccessTokenDTO;
import com.ocean.common.exceptions.AccessTokenDeletedException;
import com.ocean.common.exceptions.AccessTokenExpiresTimeException;
import com.ocean.config.SecurityConfig;
import com.ocean.config.SecurityProperty;
import com.ocean.core.authentication.OceanAuthentication;
import com.ocean.core.constants.SecurityConstants;
import com.ocean.core.model.LoginUser;
import com.ocean.web.exception.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ltx
 */
@AllArgsConstructor
public class OceanAuthenticationFilter extends OncePerRequestFilter {

    private SecurityProperty securityProperty;

    private OAuth2Api oAuth2Api;

    private GlobalExceptionHandler globalExceptionHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String token = request.getHeader(SecurityConstants.AUTHENTICATION_HEADER);
        if(token!=null){
            try{
                // 当前token存在,根据token查询是哪一个用户,生成该用于存入,SecurityContext中。
                LoginUser loginUserByToken = null;
                loginUserByToken = createLoginUserByToken(token);

                if(loginUserByToken==null){
                    loginUserByToken = createMockUser();
                }
                if(loginUserByToken==null){
                    response.sendError(HttpServletResponse.SC_FORBIDDEN,"当前访问认证不通过,请先进行登录");
                    return;
                }
                createAuthentication(loginUserByToken);
            }catch (Exception e){
                globalExceptionHandler.handlerAccessTokenException(response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
    private LoginUser createLoginUserByToken(String token){
        OAuth2AuthenticationAccessTokenDTO dto = oAuth2Api.checkAuthenticationToken(token);
        if(dto==null){
            return null;
        }
        return LoginUser.builder().id(dto.getId()).username(dto.getUsername()).accessToken(token).build();
    }
    private void createAuthentication(LoginUser loginUser){
        OceanAuthentication authentication = new OceanAuthentication();
        authentication.setUserId(loginUser.getId());
        authentication.setUsername(loginUser.getUsername());
        authentication.setUser(loginUser);
        authentication.setAuthenticated(true);
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        emptyContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(emptyContext);
    }

    private LoginUser createMockUser(){
        if(!securityProperty.getMockUser()){
            return null;
        }
        return LoginUser.builder().id(1000000L).username("mockUser").accessToken("tempToken").build();
    }
}
