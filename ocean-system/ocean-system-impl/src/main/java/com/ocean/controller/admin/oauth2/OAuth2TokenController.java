package com.ocean.controller.admin.oauth2;

import com.ocean.common.exceptions.AuthenticateFailureException;
import com.ocean.common.restful.RestfulResponse;
import com.ocean.convert.SystemOAuth2TokenConvert;
import com.ocean.enums.OAuth2ClientHeadersEnum;
import com.ocean.enums.OAuth2GrantType;
import com.ocean.model.entity.AdminUserDO;
import com.ocean.model.entity.SystemOAuth2AccessTokenDO;
import com.ocean.model.entity.SystemOAuth2ClientDO;
import com.ocean.model.vo.oauth2.OAuth2AccessTokenResVO;
import com.ocean.model.vo.oauth2.RequestAccessTokenVO;
import com.ocean.service.UserService;
import com.ocean.service.oauth2.OAuth2ClientService;
import com.ocean.service.oauth2.OAuth2TokenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author ltx
 */
@RestController
@RequestMapping("/oauth2/token")
public class OAuth2TokenController {

    @Resource
    OAuth2ClientService oAuth2ClientService;

    @Resource
    OAuth2TokenService oAuth2TokenService;

    @Resource
    UserService userService;

    @PostMapping("/get")
    @Operation(summary = "获取访问令牌、刷新访问令牌逻辑")
    public RestfulResponse<OAuth2AccessTokenResVO> getToken(HttpServletRequest request, @RequestBody RequestAccessTokenVO requestAccessTokenVO){
        OAuth2GrantType grantType = OAuth2GrantType.getGrantType(requestAccessTokenVO.getRequestType());
        String client_id = request.getHeader(OAuth2ClientHeadersEnum.CLIENT_ID.header);
        String secret = request.getHeader(OAuth2ClientHeadersEnum.SECRET.header);
        SystemOAuth2ClientDO systemOAuth2ClientDO =  oAuth2ClientService.getOAuth2ClientByClientId(client_id,secret);
        if(Objects.isNull(systemOAuth2ClientDO)){
            return RestfulResponse.fail("当前请求客户端未被管理授权",new OAuth2AccessTokenResVO());
        }
        SystemOAuth2AccessTokenDO systemOAuth2AccessTokenDO = null;
        switch(grantType){
            // 刷新令牌生成一个新的令牌返回
            case REFRESH:
                systemOAuth2AccessTokenDO = oAuth2TokenService.refreshToken(requestAccessTokenVO.getRefreshToken(),client_id);
                break;
            // 密码模式.根据用户名密码返回一个访问令牌和刷新令牌供使用
            case PASSWORD:
                AdminUserDO flag = userService.authenticateUser(requestAccessTokenVO.getUsername(), requestAccessTokenVO.getPassword());
                if(flag==null){
                    throw new AuthenticateFailureException("用户认证失败");
                }
                systemOAuth2AccessTokenDO = oAuth2TokenService.createAccessToken(flag, client_id);
                break;
            // 授权码模式,根据提供的授权码,验证请求的客户端是否可以授权,返回相应的访问令牌和刷新令牌
            case AUTHORITY_CODE:
                break;
        }
        OAuth2AccessTokenResVO vo = SystemOAuth2TokenConvert.INSTNACE.DOToAccessTokeResVO(systemOAuth2AccessTokenDO);
        return RestfulResponse.ok("获取访问令牌成功",vo);
    }
}
