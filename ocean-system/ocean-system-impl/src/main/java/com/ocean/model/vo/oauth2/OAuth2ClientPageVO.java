package com.ocean.model.vo.oauth2;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ocean.common.constants.DateConstants;
import com.ocean.common.model.PageParamVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ltx
 */
@Data
public class OAuth2ClientPageVO{
    @Schema(description = "自增id")
    private Long id;
    @Schema(description = "客户端编号")
    private String clientId;
    @Schema(description = "客户端秘钥")
    private String secret;
    @Schema(description = "应用名称")
    private String name;
    @Schema(description = "应用图标")
    private String logo;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "访问令牌的有效期")
    private Long accessTokenValiditySeconds;
    @Schema(description = "刷新令牌的有效期")
    private Long refreshTokenValiditySeconds;
    @Schema(description = "授权类型")
    private List<String> authorizedGrantTypes;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateConstants.DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS)
    private LocalDateTime createTime;
}
