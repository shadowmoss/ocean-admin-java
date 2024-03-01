package com.ocean.config;

import api.permission.DataPermissionApi;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.ocean.core.DataPermissionInterceptor;
import com.ocean.core.factory.DataPermissionRuleFactory;
import com.ocean.core.factory.DataPermissionRuleFactoryImpl;
import com.ocean.core.rule.DataPermissionCustomizer;
import com.ocean.core.rule.DataPermissionRule;
import com.ocean.core.rule.DeptDataPermissionRule;
import org.mapstruct.Condition;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataPermissionAutoConfiguration {
    @Bean
    public DeptDataPermissionRule deptDataPermissionRule(DataPermissionApi dataPermissionApi, List<DataPermissionCustomizer> customizers){
        // 部门数据权限对象
        DeptDataPermissionRule deptDataPermissionRule = new DeptDataPermissionRule(dataPermissionApi);
        customizers.forEach(item-> item.customizer(deptDataPermissionRule));
        return deptDataPermissionRule;
    }
    /**
     * 数据权限规则Rule
     * @param rules
     * @return
     */
    @Bean
    public DataPermissionRuleFactory dataPermissionRuleFactory(List<DataPermissionRule> rules){
        return new DataPermissionRuleFactoryImpl(rules);
    }

    /**
     * 数据权限sql拦截器
     * @param mybatisPlusInterceptor
     * @return
     */
    @Bean
    public DataPermissionInterceptor dataPermissionInterceptor(MybatisPlusInterceptor mybatisPlusInterceptor,DataPermissionRuleFactory factory) {
        List<InnerInterceptor> interceptors = new ArrayList<>(mybatisPlusInterceptor.getInterceptors());
        DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor(factory);
        // sql改写类拦截器放置在拦截器列表最前端。
        interceptors.add(0, dataPermissionInterceptor);
        mybatisPlusInterceptor.setInterceptors(interceptors);
        return dataPermissionInterceptor;
    }
}
