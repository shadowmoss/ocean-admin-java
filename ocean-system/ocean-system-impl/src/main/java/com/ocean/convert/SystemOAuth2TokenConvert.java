package com.ocean.convert;

import com.ocean.model.entity.SystemOAuth2AccessTokenDO;
import com.ocean.model.vo.oauth2.OAuth2AccessTokenResVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ltx
 */
@Mapper
public interface SystemOAuth2TokenConvert {
    public SystemOAuth2TokenConvert INSTNACE = Mappers.getMapper(SystemOAuth2TokenConvert.class);

    OAuth2AccessTokenResVO DOToAccessTokeResVO(SystemOAuth2AccessTokenDO systemOAuth2AccessTokenDO);
}
