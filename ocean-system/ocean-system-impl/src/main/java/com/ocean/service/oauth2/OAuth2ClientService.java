package com.ocean.service.oauth2;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.model.entity.SystemOAuth2ClientDO;
import com.ocean.model.vo.oauth2.*;

/**
 * @author ltx
 */
public interface OAuth2ClientService {
    Boolean createNewOAuth2Client(CreateNewOAuth2ClientVO createNewOAuth2ClientVO);

    Boolean updateNewOAuth2Client(UpdateNewOAuth2ClientVO updateNewOAuth2ClientVO);

    Boolean deleteOAuth2Client(Long id);

    Page<OAuth2ClientPageVO> pageOAuth2Client(OAuth2ClientPageReqVO oAuth2ClientPageReqVO);

    OAuth2ClientDetailsVO getOAuth2ClientDetails(Long id);

    SystemOAuth2ClientDO getOAuth2ClientByClientId(String client_id, String secret);
}
