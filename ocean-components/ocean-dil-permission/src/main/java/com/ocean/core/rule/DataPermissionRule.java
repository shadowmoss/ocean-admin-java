package com.ocean.core.rule;


import com.ocean.core.model.BaseDO;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;

import java.util.Set;

public interface DataPermissionRule {
    Set<String> getTableNames();
    /**
     * 输入表名，表别名。根据表名返回响应的条件表达式
     * @return
     */
    Expression getPermissionExpression(String tableName, Alias tableAlias);

    /**
     * 提供一个根据Class获取表名的方法
     */
    void addTableColumn(Class<? extends BaseDO> clazz,String columnName);
}
