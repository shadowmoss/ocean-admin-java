package com.ocean.core.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

/**
 * @author ltx
 */
@Data
public abstract class BaseDO {
    /*
    * 数据的创建时间,配置了MybatisPlus注解,在插入时填充字段
    * */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /*
    * 数据的更新时间,配置了MybatisPlus注解,在插入和更新时注入新值
    * */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 数据的创建人
     */
    @TableField(fill = FieldFill.INSERT,jdbcType = JdbcType.VARCHAR)
    private String creator;

    /**
     * 数据的更新者
     */
    @TableField(fill = FieldFill.INSERT,jdbcType = JdbcType.VARCHAR)
    private String updater;

    /**
     * 是否逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
