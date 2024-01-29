package com.ocean.common.restful;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ltx
 */
@Data
public class RestfulPageResult<T>{
    /**
     * 分页记录
     */
    private List<T> records;
    /**
     * 当前页号
     */
    private Long currentPageNo;
    /**
     * 当前记录数
     */
    private Long total;
    /**
     * 当前记录总数
     */
    private Long size;
}
