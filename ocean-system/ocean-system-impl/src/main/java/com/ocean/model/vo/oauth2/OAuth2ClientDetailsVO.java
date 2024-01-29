package com.ocean.model.vo.oauth2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2ClientDetailsVO extends BaseOAuth2ClientVO{
    @Schema(description = "自增id")
    private Long id;

}
