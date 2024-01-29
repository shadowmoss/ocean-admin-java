package com.ocean.model.vo.oauth2;

import com.ocean.common.model.PageParamVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2ClientPageReqVO extends PageParamVO {
    @Schema(description = "应用名称")
    private String name;
    @Schema(description = "停启用状态,0:启用,1:停用")
    private Integer status;
}
