package com.ocean.controller.admin.dept.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentUpdateVO extends DepartmentBaseVO{
    private Long id;
}
