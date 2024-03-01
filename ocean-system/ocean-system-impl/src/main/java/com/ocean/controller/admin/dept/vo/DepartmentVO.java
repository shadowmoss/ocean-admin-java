package com.ocean.controller.admin.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Schema(description = "部门查询")
@EqualsAndHashCode(callSuper = true)
public class DepartmentVO extends DepartmentBaseVO{
    /**
     * 自增id
     */
    private Long id;
    /**
     * 子树
     */
    private List<DepartmentVO> children;
}
