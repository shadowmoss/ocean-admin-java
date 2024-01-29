package api.oauth2;

import api.oauth2.dto.OAuth2AuthenticationAccessTokenDTO;
import com.ocean.common.exceptions.AccessTokenDeletedException;
import com.ocean.common.exceptions.AccessTokenExpiresTimeException;

/**
 * @author ltx
 */
public interface OAuth2Api {
    OAuth2AuthenticationAccessTokenDTO checkAuthenticationToken(String token);
}
