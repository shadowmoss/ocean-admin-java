package com.ocean.convert;

import com.ocean.model.entity.SystemOAuth2AccessTokenDO;
import com.ocean.model.vo.oauth2.OAuth2AccessTokenResVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-29T22:12:37+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_391 (Oracle Corporation)"
)
public class SystemOAuth2TokenConvertImpl implements SystemOAuth2TokenConvert {

    @Override
    public OAuth2AccessTokenResVO DOToAccessTokeResVO(SystemOAuth2AccessTokenDO systemOAuth2AccessTokenDO) {
        if ( systemOAuth2AccessTokenDO == null ) {
            return null;
        }

        OAuth2AccessTokenResVO oAuth2AccessTokenResVO = new OAuth2AccessTokenResVO();

        oAuth2AccessTokenResVO.setAccessToken( systemOAuth2AccessTokenDO.getAccessToken() );
        oAuth2AccessTokenResVO.setRefreshToken( systemOAuth2AccessTokenDO.getRefreshToken() );
        oAuth2AccessTokenResVO.setExpiresTime( systemOAuth2AccessTokenDO.getExpiresTime() );

        return oAuth2AccessTokenResVO;
    }
}
