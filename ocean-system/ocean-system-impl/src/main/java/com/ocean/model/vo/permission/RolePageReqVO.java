package com.ocean.model.vo.permission;

import com.ocean.common.constants.DateConstants;
import com.ocean.common.model.PageParamVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author ltx
 */
@Data
@Schema(description = "角色分页查询条件")
@EqualsAndHashCode(callSuper = true)
public class RolePageReqVO extends PageParamVO {
    @Schema(description = "角色名称")
    private String name;
    @Schema(description = "角色状态,0:启用,1:停用")
    private Integer status;
    @Schema(description = "角色标识")
    private String code;
    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = DateConstants.DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS)
    private LocalDateTime startTime;
    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = DateConstants.DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS)
    private LocalDateTime endTime;
}
