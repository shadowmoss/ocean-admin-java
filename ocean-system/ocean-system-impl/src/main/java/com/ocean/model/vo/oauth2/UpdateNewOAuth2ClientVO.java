package com.ocean.model.vo.oauth2;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateNewOAuth2ClientVO extends BaseOAuth2ClientVO{
    /**
     * 自增id
     */
    private Long id;
}
