package com.ocean.common.model;

import lombok.Data;

/**
 * @author ltx
 */
@Data

public class PageParamVO {
    /**
     * 当前页号
     */
    private Integer currentPageNo;
    /**
     * 当前分页条数
     */
    private Integer pageSize;
}
