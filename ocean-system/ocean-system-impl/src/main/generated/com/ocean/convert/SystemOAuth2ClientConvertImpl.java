package com.ocean.convert;

import com.ocean.model.entity.SystemOAuth2ClientDO;
import com.ocean.model.vo.oauth2.CreateNewOAuth2ClientVO;
import com.ocean.model.vo.oauth2.OAuth2ClientDetailsVO;
import com.ocean.model.vo.oauth2.OAuth2ClientPageVO;
import com.ocean.model.vo.oauth2.UpdateNewOAuth2ClientVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-29T22:12:37+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_391 (Oracle Corporation)"
)
public class SystemOAuth2ClientConvertImpl implements SystemOAuth2ClientConvert {

    @Override
    public SystemOAuth2ClientDO createVOToDO(CreateNewOAuth2ClientVO createNewOAuth2ClientVO) {
        if ( createNewOAuth2ClientVO == null ) {
            return null;
        }

        SystemOAuth2ClientDO systemOAuth2ClientDO = new SystemOAuth2ClientDO();

        systemOAuth2ClientDO.setClientId( createNewOAuth2ClientVO.getClientId() );
        systemOAuth2ClientDO.setSecret( createNewOAuth2ClientVO.getSecret() );
        systemOAuth2ClientDO.setName( createNewOAuth2ClientVO.getName() );
        systemOAuth2ClientDO.setLogo( createNewOAuth2ClientVO.getLogo() );
        systemOAuth2ClientDO.setDescription( createNewOAuth2ClientVO.getDescription() );
        systemOAuth2ClientDO.setStatus( createNewOAuth2ClientVO.getStatus() );
        if ( createNewOAuth2ClientVO.getAccessTokenValiditySeconds() != null ) {
            systemOAuth2ClientDO.setAccessTokenValiditySeconds( createNewOAuth2ClientVO.getAccessTokenValiditySeconds().longValue() );
        }
        if ( createNewOAuth2ClientVO.getRefreshTokenValiditySeconds() != null ) {
            systemOAuth2ClientDO.setRefreshTokenValiditySeconds( createNewOAuth2ClientVO.getRefreshTokenValiditySeconds().longValue() );
        }
        List<String> list = createNewOAuth2ClientVO.getRedirectUris();
        if ( list != null ) {
            systemOAuth2ClientDO.setRedirectUris( new ArrayList<String>( list ) );
        }
        List<String> list1 = createNewOAuth2ClientVO.getAuthorizedGrantTypes();
        if ( list1 != null ) {
            systemOAuth2ClientDO.setAuthorizedGrantTypes( new ArrayList<String>( list1 ) );
        }

        return systemOAuth2ClientDO;
    }

    @Override
    public SystemOAuth2ClientDO updateVOToDO(UpdateNewOAuth2ClientVO updateNewOAuth2ClientVO) {
        if ( updateNewOAuth2ClientVO == null ) {
            return null;
        }

        SystemOAuth2ClientDO systemOAuth2ClientDO = new SystemOAuth2ClientDO();

        systemOAuth2ClientDO.setId( updateNewOAuth2ClientVO.getId() );
        systemOAuth2ClientDO.setClientId( updateNewOAuth2ClientVO.getClientId() );
        systemOAuth2ClientDO.setSecret( updateNewOAuth2ClientVO.getSecret() );
        systemOAuth2ClientDO.setName( updateNewOAuth2ClientVO.getName() );
        systemOAuth2ClientDO.setLogo( updateNewOAuth2ClientVO.getLogo() );
        systemOAuth2ClientDO.setDescription( updateNewOAuth2ClientVO.getDescription() );
        systemOAuth2ClientDO.setStatus( updateNewOAuth2ClientVO.getStatus() );
        if ( updateNewOAuth2ClientVO.getAccessTokenValiditySeconds() != null ) {
            systemOAuth2ClientDO.setAccessTokenValiditySeconds( updateNewOAuth2ClientVO.getAccessTokenValiditySeconds().longValue() );
        }
        if ( updateNewOAuth2ClientVO.getRefreshTokenValiditySeconds() != null ) {
            systemOAuth2ClientDO.setRefreshTokenValiditySeconds( updateNewOAuth2ClientVO.getRefreshTokenValiditySeconds().longValue() );
        }
        List<String> list = updateNewOAuth2ClientVO.getRedirectUris();
        if ( list != null ) {
            systemOAuth2ClientDO.setRedirectUris( new ArrayList<String>( list ) );
        }
        List<String> list1 = updateNewOAuth2ClientVO.getAuthorizedGrantTypes();
        if ( list1 != null ) {
            systemOAuth2ClientDO.setAuthorizedGrantTypes( new ArrayList<String>( list1 ) );
        }

        return systemOAuth2ClientDO;
    }

    @Override
    public OAuth2ClientPageVO DOToPageVO(SystemOAuth2ClientDO item) {
        if ( item == null ) {
            return null;
        }

        OAuth2ClientPageVO oAuth2ClientPageVO = new OAuth2ClientPageVO();

        oAuth2ClientPageVO.setId( item.getId() );
        oAuth2ClientPageVO.setClientId( item.getClientId() );
        oAuth2ClientPageVO.setSecret( item.getSecret() );
        oAuth2ClientPageVO.setName( item.getName() );
        oAuth2ClientPageVO.setLogo( item.getLogo() );
        oAuth2ClientPageVO.setStatus( item.getStatus() );
        oAuth2ClientPageVO.setAccessTokenValiditySeconds( item.getAccessTokenValiditySeconds() );
        oAuth2ClientPageVO.setRefreshTokenValiditySeconds( item.getRefreshTokenValiditySeconds() );
        List<String> list = item.getAuthorizedGrantTypes();
        if ( list != null ) {
            oAuth2ClientPageVO.setAuthorizedGrantTypes( new ArrayList<String>( list ) );
        }
        oAuth2ClientPageVO.setCreateTime( item.getCreateTime() );

        return oAuth2ClientPageVO;
    }

    @Override
    public OAuth2ClientDetailsVO DOToDetailsVO(SystemOAuth2ClientDO systemOAuth2ClientDO) {
        if ( systemOAuth2ClientDO == null ) {
            return null;
        }

        OAuth2ClientDetailsVO oAuth2ClientDetailsVO = new OAuth2ClientDetailsVO();

        oAuth2ClientDetailsVO.setClientId( systemOAuth2ClientDO.getClientId() );
        oAuth2ClientDetailsVO.setSecret( systemOAuth2ClientDO.getSecret() );
        oAuth2ClientDetailsVO.setName( systemOAuth2ClientDO.getName() );
        oAuth2ClientDetailsVO.setLogo( systemOAuth2ClientDO.getLogo() );
        oAuth2ClientDetailsVO.setDescription( systemOAuth2ClientDO.getDescription() );
        oAuth2ClientDetailsVO.setStatus( systemOAuth2ClientDO.getStatus() );
        if ( systemOAuth2ClientDO.getAccessTokenValiditySeconds() != null ) {
            oAuth2ClientDetailsVO.setAccessTokenValiditySeconds( systemOAuth2ClientDO.getAccessTokenValiditySeconds().intValue() );
        }
        if ( systemOAuth2ClientDO.getRefreshTokenValiditySeconds() != null ) {
            oAuth2ClientDetailsVO.setRefreshTokenValiditySeconds( systemOAuth2ClientDO.getRefreshTokenValiditySeconds().intValue() );
        }
        List<String> list = systemOAuth2ClientDO.getAuthorizedGrantTypes();
        if ( list != null ) {
            oAuth2ClientDetailsVO.setAuthorizedGrantTypes( new ArrayList<String>( list ) );
        }
        List<String> list1 = systemOAuth2ClientDO.getRedirectUris();
        if ( list1 != null ) {
            oAuth2ClientDetailsVO.setRedirectUris( new ArrayList<String>( list1 ) );
        }
        oAuth2ClientDetailsVO.setId( systemOAuth2ClientDO.getId() );

        return oAuth2ClientDetailsVO;
    }
}
