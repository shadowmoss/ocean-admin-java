package com.ocean.model.vo.permission;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ocean.common.constants.DateConstants;
import com.sun.javafx.menu.MenuBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuResVO extends MenuBaseVO {
    @Schema(description = "自增id")
    private Long id;
    @Schema(description = "子目录")
    private List<MenuResVO> children;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateConstants.DATE_YEAR_MONTH_DAYS_HOUR_MINTE_SECONDS)
    private LocalDateTime createTime;
}
