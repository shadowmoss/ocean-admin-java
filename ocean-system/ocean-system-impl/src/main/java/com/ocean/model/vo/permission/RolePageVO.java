package com.ocean.model.vo.permission;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ocean.common.constants.DateConstants;
import com.ocean.common.model.PageParamVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ltx
 */
@Data
@Schema(description = "角色分页查询")
public class RolePageVO{
    @Schema(description = "自增id")
    private Long id;
    @Schema(description = "角色名称")
    private String name;
    @Schema(description = "角色类型")
    private Integer roleType;
    @Schema(description = "角色标识")
    private String code;
    @Schema(description = "角色显示顺序")
    private Integer roleOrder;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "角色状态,0:表示启用,1:表示停用")
    private Integer status;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateConstants.DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS)
    private LocalDateTime createTime;
}
