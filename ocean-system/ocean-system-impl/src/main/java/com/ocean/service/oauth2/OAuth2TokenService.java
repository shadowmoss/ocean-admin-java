package com.ocean.service.oauth2;

import api.oauth2.dto.OAuth2AuthenticationAccessTokenDTO;
import com.ocean.common.exceptions.AccessTokenDeletedException;
import com.ocean.common.exceptions.AccessTokenExpiresTimeException;
import com.ocean.model.entity.AdminUserDO;
import com.ocean.model.entity.SystemOAuth2AccessTokenDO;
import com.ocean.model.entity.SystemOAuth2RefreshTokenDO;
import com.ocean.model.vo.UserLoginResVO;

/**
 * @author ltx
 */
public interface OAuth2TokenService {
    SystemOAuth2AccessTokenDO createAccessToken(AdminUserDO adminUserDO,String clientId);

    OAuth2AuthenticationAccessTokenDTO checkAccessToken(String token) throws AccessTokenExpiresTimeException, AccessTokenDeletedException;

    Boolean logOut(String accessToken);

    SystemOAuth2RefreshTokenDO createRefreshToken(AdminUserDO adminUserDO,String clientId,Long userId);

    SystemOAuth2AccessTokenDO refreshToken(String refreshToken, String client_id);

    SystemOAuth2AccessTokenDO createNewAccessTokeByRefreshToken(String refreshToken,String clientId,Long userId);
}
