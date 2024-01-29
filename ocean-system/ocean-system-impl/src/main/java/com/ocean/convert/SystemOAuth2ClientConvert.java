package com.ocean.convert;

import com.ocean.model.entity.SystemOAuth2ClientDO;
import com.ocean.model.vo.oauth2.CreateNewOAuth2ClientVO;
import com.ocean.model.vo.oauth2.OAuth2ClientDetailsVO;
import com.ocean.model.vo.oauth2.OAuth2ClientPageVO;
import com.ocean.model.vo.oauth2.UpdateNewOAuth2ClientVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author ltx
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemOAuth2ClientConvert {
    public SystemOAuth2ClientConvert INSTANCE = Mappers.getMapper(SystemOAuth2ClientConvert.class);

    SystemOAuth2ClientDO createVOToDO(CreateNewOAuth2ClientVO createNewOAuth2ClientVO);

    SystemOAuth2ClientDO updateVOToDO(UpdateNewOAuth2ClientVO updateNewOAuth2ClientVO);

    OAuth2ClientPageVO DOToPageVO(SystemOAuth2ClientDO item);

    OAuth2ClientDetailsVO DOToDetailsVO(SystemOAuth2ClientDO systemOAuth2ClientDO);
}
