package com.ocean.service.oauth2;

import api.oauth2.dto.OAuth2AuthenticationAccessTokenDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ocean.common.exceptions.AccessTokenDeletedException;
import com.ocean.common.exceptions.AccessTokenExpiresTimeException;
import com.ocean.common.exceptions.RefreshTokenExpireException;
import com.ocean.common.exceptions.RefreshTokenNotExistException;
import com.ocean.mapper.oauth2.SystemOAuth2AccessTokenMapper;
import com.ocean.mapper.oauth2.SystemOAuth2ClientMapper;
import com.ocean.mapper.oauth2.SystemOAuth2RefreshTokenMapper;
import com.ocean.model.entity.AdminUserDO;
import com.ocean.model.entity.SystemOAuth2AccessTokenDO;
import com.ocean.model.entity.SystemOAuth2ClientDO;
import com.ocean.model.entity.SystemOAuth2RefreshTokenDO;
import com.ocean.model.vo.UserLoginResVO;
import com.ocean.utils.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author ltx
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService{
    @Resource
    SystemOAuth2AccessTokenMapper systemOAuth2AccessTokenMapper;
    @Resource
    SystemOAuth2ClientMapper systemOAuth2ClientMapper;

    @Resource
    SystemOAuth2RefreshTokenMapper systemOAuth2RefreshTokenMapper;
    @Override
    public SystemOAuth2AccessTokenDO createAccessToken(AdminUserDO adminUserDO,String clientId) {
        LambdaQueryWrapper<SystemOAuth2ClientDO> clientDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clientDOLambdaQueryWrapper.eq(SystemOAuth2ClientDO::getClientId,clientId);
        SystemOAuth2ClientDO systemOAuth2ClientDO = systemOAuth2ClientMapper.selectOne(clientDOLambdaQueryWrapper);
        // 获取默认客户端允许的访问令牌有效时间
        Long accessTokenValiditySeconds = systemOAuth2ClientDO.getAccessTokenValiditySeconds();
        LocalDateTime accessTokenValidTime = LocalDateTime.now().plusSeconds(accessTokenValiditySeconds);
        // 生成访问令牌Entity
        SystemOAuth2AccessTokenDO systemOAuth2AccessTokenDO = new SystemOAuth2AccessTokenDO();
        systemOAuth2AccessTokenDO.setClientId(clientId);
        systemOAuth2AccessTokenDO.setUserId(adminUserDO.getId());
        systemOAuth2AccessTokenDO.setAccessToken(TokenUtil.getAccessToken());
        systemOAuth2AccessTokenDO.setExpiresTime(accessTokenValidTime);
        systemOAuth2AccessTokenDO.setRefreshToken(createRefreshToken(adminUserDO,clientId,adminUserDO.getId()).getRefreshToken());
        systemOAuth2AccessTokenMapper.insert(systemOAuth2AccessTokenDO);
        return systemOAuth2AccessTokenDO;
    }

    /**
     * 根据访问令牌查询访问令牌信息
     * @param token
     * @return
     */
    @Override
    public OAuth2AuthenticationAccessTokenDTO checkAccessToken(String token){
        LambdaQueryWrapper<SystemOAuth2AccessTokenDO> accessTokenDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        accessTokenDOLambdaQueryWrapper.eq(SystemOAuth2AccessTokenDO::getAccessToken,token);
        SystemOAuth2AccessTokenDO systemOAuth2AccessTokenDO = systemOAuth2AccessTokenMapper.selectOne(accessTokenDOLambdaQueryWrapper);
        if(systemOAuth2AccessTokenDO==null){
            throw new AccessTokenDeletedException("访问令牌已被删除");
        }
        LocalDateTime expiresTime = systemOAuth2AccessTokenDO.getExpiresTime();
        LocalDateTime now = LocalDateTime.now();
        if(now.compareTo(expiresTime)>0){
            throw new AccessTokenExpiresTimeException("访问令牌过期");
        }
        return systemOAuth2AccessTokenMapper.authenticationUserByAccessToken(token);
    }

    @Override
    public Boolean logOut(String accessToken) {
        LambdaQueryWrapper<SystemOAuth2AccessTokenDO> systemOAuth2AccessTokenDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        systemOAuth2AccessTokenDOLambdaQueryWrapper.eq(SystemOAuth2AccessTokenDO::getAccessToken,accessToken);
        int delete = systemOAuth2AccessTokenMapper.delete(systemOAuth2AccessTokenDOLambdaQueryWrapper);
        if(delete>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public SystemOAuth2RefreshTokenDO createRefreshToken(AdminUserDO adminUserDO,String clientId,Long userId) {
        LambdaQueryWrapper<SystemOAuth2ClientDO> clientDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clientDOLambdaQueryWrapper.eq(SystemOAuth2ClientDO::getClientId,clientId);
        SystemOAuth2ClientDO systemOAuth2ClientDO = systemOAuth2ClientMapper.selectOne(clientDOLambdaQueryWrapper);
        Long refreshTokenValiditySeconds = systemOAuth2ClientDO.getRefreshTokenValiditySeconds();
        LocalDateTime expiresTime = LocalDateTime.now().plusSeconds(refreshTokenValiditySeconds);
        // 生成刷新令牌Entity
        SystemOAuth2RefreshTokenDO systemOAuth2RefreshTokenDO = new SystemOAuth2RefreshTokenDO();
        systemOAuth2RefreshTokenDO.setClientId(clientId);
        systemOAuth2RefreshTokenDO.setUserId(userId);
        systemOAuth2RefreshTokenDO.setRefreshToken(TokenUtil.getRefreshToken());
        systemOAuth2RefreshTokenDO.setExpiresTime(expiresTime);
        systemOAuth2RefreshTokenMapper.insert(systemOAuth2RefreshTokenDO);
        return systemOAuth2RefreshTokenDO;
    }

    @Override
    public SystemOAuth2AccessTokenDO refreshToken(String refreshToken, String client_id) {
        // 查询刷新令牌信息
        LambdaQueryWrapper<SystemOAuth2RefreshTokenDO> tokenDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tokenDOLambdaQueryWrapper.eq(SystemOAuth2RefreshTokenDO::getRefreshToken,refreshToken)
                                    .eq(SystemOAuth2RefreshTokenDO::getClientId,client_id);
        SystemOAuth2RefreshTokenDO systemOAuth2RefreshTokenDO = systemOAuth2RefreshTokenMapper.selectOne(tokenDOLambdaQueryWrapper);
        if(Objects.isNull(systemOAuth2RefreshTokenDO)){
            throw new RefreshTokenNotExistException("刷新令牌不存在");
        }
        LocalDateTime now = LocalDateTime.now();
        if(now.compareTo(systemOAuth2RefreshTokenDO.getExpiresTime())>0){
            throw new RefreshTokenExpireException("刷新令牌已过期");
        }
        LambdaQueryWrapper<SystemOAuth2AccessTokenDO> accessTokenDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        accessTokenDOLambdaQueryWrapper.eq(SystemOAuth2AccessTokenDO::getRefreshToken,refreshToken)
                                        .eq(SystemOAuth2AccessTokenDO::getClientId,client_id);
        // 移除对应的访问令牌
        List<SystemOAuth2AccessTokenDO> systemOAuth2AccessTokenDOS = systemOAuth2AccessTokenMapper.selectList(accessTokenDOLambdaQueryWrapper);
        for(SystemOAuth2AccessTokenDO item :systemOAuth2AccessTokenDOS){
            systemOAuth2AccessTokenMapper.deleteById(item.getId());
        }

        // 生成新的访问令牌
        return createNewAccessTokeByRefreshToken(refreshToken,client_id,systemOAuth2RefreshTokenDO.getUserId());
    }

    @Override
    public SystemOAuth2AccessTokenDO createNewAccessTokeByRefreshToken(String refreshToken, String clientId,Long userId) {
        LambdaQueryWrapper<SystemOAuth2ClientDO> clientDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clientDOLambdaQueryWrapper.eq(SystemOAuth2ClientDO::getClientId,clientId);
        SystemOAuth2ClientDO systemOAuth2ClientDO = systemOAuth2ClientMapper.selectOne(clientDOLambdaQueryWrapper);
        // 获取默认客户端允许的访问令牌有效时间
        Long accessTokenValiditySeconds = systemOAuth2ClientDO.getAccessTokenValiditySeconds();
        LocalDateTime accessTokenValidTime = LocalDateTime.now().plusSeconds(accessTokenValiditySeconds);
        // 生成访问令牌Entity
        SystemOAuth2AccessTokenDO systemOAuth2AccessTokenDO = new SystemOAuth2AccessTokenDO();
        systemOAuth2AccessTokenDO.setClientId(clientId);
        systemOAuth2AccessTokenDO.setUserId(userId);
        systemOAuth2AccessTokenDO.setAccessToken(TokenUtil.getAccessToken());
        systemOAuth2AccessTokenDO.setExpiresTime(accessTokenValidTime);
        systemOAuth2AccessTokenDO.setRefreshToken(refreshToken);
        systemOAuth2AccessTokenMapper.insert(systemOAuth2AccessTokenDO);
        return systemOAuth2AccessTokenDO;
    }


}
