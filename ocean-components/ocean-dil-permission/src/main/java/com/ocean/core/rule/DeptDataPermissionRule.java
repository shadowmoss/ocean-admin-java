package com.ocean.core.rule;

import api.permission.DataPermissionApi;
import api.permission.dto.DeptDataPermissionDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.ocean.core.model.BaseDO;
import com.ocean.core.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.apache.ibatis.javassist.bytecode.annotation.LongMemberValue;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DeptDataPermissionRule implements DataPermissionRule {

    private static final String DEPT_COLUMN_NAME = "dept_id";
    private final Set<String> TABLE_NAMES = new HashSet<>();

    private final Map<String,String> tableColumn = new HashMap<>();

    private final DataPermissionApi dataPermissionApi;

    @Override
    public Set<String> getTableNames() {
        return TABLE_NAMES;
    }

    @Override
    public Expression getPermissionExpression(String tableName, Alias tableAlias) {
        Long authenticatedUserId = SecurityUtil.getAuthenticatedUserId();
        DeptDataPermissionDTO dataPermissionResult =  dataPermissionApi.getUserRelationDept(authenticatedUserId);
        if (dataPermissionResult.getAll()){
            return null;
        }
        /**
         * 如果条件是查看自己所属部门
         */
        Expression deptExpression = null;
        if(dataPermissionResult.getSelfDept()){
            deptExpression = buildExpression(tableName,tableAlias,dataPermissionResult.getDeptIds());
        }

        return deptExpression;
    }

    @Override
    public void addTableColumn(Class<? extends BaseDO> clazz, String columnName) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        String tableName = tableInfo.getTableName();
        tableColumn.put(tableName,columnName);
        TABLE_NAMES.add(tableName);
    }

    private Expression buildExpression(String tableName,Alias tableAlias,Set<Long> deptIds){
        Expression expression;
        String columnName = tableColumn.get(tableName);
        if(StringUtils.hasLength(columnName)){
            return null;
        }
        if(CollectionUtils.isEmpty(deptIds)){
            return null;
        }
        // 拼接语句
        return new InExpression(new Column((tableAlias!=null ? tableAlias:tableName)+StringPool.DOT+columnName),
               new ExpressionList(deptIds.stream().map(LongValue::new).filter(Objects::nonNull).collect(Collectors.toList())));
    }
}
