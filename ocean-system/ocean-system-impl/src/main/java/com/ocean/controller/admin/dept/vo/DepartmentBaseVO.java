package com.ocean.controller.admin.dept.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentBaseVO {
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父部门id
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 负责人
     */
    private Long leaderId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
