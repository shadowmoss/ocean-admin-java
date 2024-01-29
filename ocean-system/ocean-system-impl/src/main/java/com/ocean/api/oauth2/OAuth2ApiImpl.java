package com.ocean.api.oauth2;

import api.oauth2.OAuth2Api;
import api.oauth2.dto.OAuth2AuthenticationAccessTokenDTO;
import com.ocean.common.exceptions.AccessTokenDeletedException;
import com.ocean.common.exceptions.AccessTokenExpiresTimeException;
import com.ocean.service.oauth2.OAuth2TokenService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ltx
 */
@Service
public class OAuth2ApiImpl implements OAuth2Api {
    @Resource
    private OAuth2TokenService oAuth2TokenService;
    @Override
    public OAuth2AuthenticationAccessTokenDTO checkAuthenticationToken(String token){
        return oAuth2TokenService.checkAccessToken(token);
    }
}
