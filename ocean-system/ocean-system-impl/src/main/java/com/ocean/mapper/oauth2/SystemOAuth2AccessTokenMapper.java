package com.ocean.mapper.oauth2;

import api.oauth2.dto.OAuth2AuthenticationAccessTokenDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.model.entity.SystemOAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ltx
 */
@Mapper
public interface SystemOAuth2AccessTokenMapper extends BaseMapper<SystemOAuth2AccessTokenDO> {
    OAuth2AuthenticationAccessTokenDTO authenticationUserByAccessToken(@Param("token") String token);
}
