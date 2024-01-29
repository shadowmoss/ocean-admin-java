package com.ocean.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ocean.common.constants.DateConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ltx
 */
@Data
@Schema(name = "后台用户分页结果")
public class AdminUserPageResVO {
    @Schema(name = "自增Id")
    private Long id;
    @Schema(name = "用户名")
    private String username;
    @Schema(name = "昵称")
    private String nickname;
    @Schema(name = "电话")
    private String telephone;
    @Schema(name = "用户状态")
    private Integer status;
    @JsonFormat(pattern = DateConstants.DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS)
    private LocalDateTime createTime;
}
