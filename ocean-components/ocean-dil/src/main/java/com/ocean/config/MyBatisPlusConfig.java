package com.ocean.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.ocean.core.fill.DefaultMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ltx
 */
@Configuration
public class MyBatisPlusConfig {
    // mybatis自动填充对应功能实现。
    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(){
        return new DefaultMetaObjectHandler();
    }

    /**
     * 分页插件注册进Spring容器
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
