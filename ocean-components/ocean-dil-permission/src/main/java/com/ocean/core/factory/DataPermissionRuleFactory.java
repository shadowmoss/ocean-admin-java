package com.ocean.core.factory;

import com.ocean.core.rule.DataPermissionRule;

import java.util.List;

public interface DataPermissionRuleFactory {
    List<DataPermissionRule> getDataPermissionRule(String mappedStatementId);
}
