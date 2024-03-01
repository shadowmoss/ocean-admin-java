package com.ocean.core.factory;

import com.ocean.core.annotation.DataPermission;
import com.ocean.core.rule.DataPermissionRule;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class DataPermissionRuleFactoryImpl implements DataPermissionRuleFactory{
    private final List<DataPermissionRule> rules;
    @Override
    public List<DataPermissionRule> getDataPermissionRule(String mappedStatementId) {
        // 1. 无数据权限
        if(CollectionUtils.isEmpty(rules)){
            return Collections.emptyList();
        }
        // 2. 当前接口未进行权限配置,默认使用所有数据权限
        DataPermission dataPermission = null;
        if(dataPermission == null){
            return rules;
        }
        if(!dataPermission.enable()){
            return Collections.emptyList();
        }
        return rules;
    }
}
