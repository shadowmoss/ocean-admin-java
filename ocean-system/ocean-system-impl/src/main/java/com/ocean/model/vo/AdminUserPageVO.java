package com.ocean.model.vo;

import com.ocean.common.constants.DateConstants;
import com.ocean.common.model.PageParamVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ocean.common.constants.DateConstants.DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS;

/**
 * @author ltx
 */
@Data
@Schema(description = "后台用户分页")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdminUserPageVO extends PageParamVO {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS)
    private LocalDateTime startTime;
    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS)
    private LocalDateTime endTime;
}
