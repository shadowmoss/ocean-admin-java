package api.oauth2.dto;

import lombok.Data;

/**
 * @author ltx
 */
@Data
public class OAuth2AuthenticationAccessTokenDTO {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 访问令牌
     */
    private String accessToken;
}
