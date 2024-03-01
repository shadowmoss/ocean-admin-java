package com.ocean.core;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.ocean.core.context.DataPermissionInterceptorContext;
import com.ocean.core.factory.DataPermissionRuleFactory;
import com.ocean.core.rule.DataPermissionRule;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class DataPermissionInterceptor extends JsqlParserSupport implements InnerInterceptor {
    private final DataPermissionRuleFactory ruleFactory;
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        // 获得 Mapper 对应的数据权限的规则
        List<DataPermissionRule> rules = ruleFactory.getDataPermissionRule(ms.getId());
        String sql =  boundSql.getSql();
        PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
        // parserSingle方法处理sql,会执行processSelect方法，需要在当前类提供实现
        // 最终生成一个新sql设置到boundSql中，进行之后的数据库操作。
        System.out.println("**** ==" + boundSql.getSql()+"**** ==");
        try{
            DataPermissionInterceptorContext.init(rules);
            mpBoundSql.sql(parserSingle(mpBoundSql.sql(),null));
        }catch (Exception e){
            DataPermissionInterceptorContext.clear();
        }

    }

    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        SelectBody selectBody = select.getSelectBody();
        List<WithItem> withItemsList = select.getWithItemsList();
    }

    private void processSelectBody(SelectBody selectBody){
        if(selectBody instanceof PlainSelect){
            processPlainSelect((PlainSelect)selectBody);
        }else if (selectBody instanceof WithItem){
            processSelectBody(((WithItem) selectBody).getSelectBody());
        }
    }

    // 处理原生Select语句
    private void processPlainSelect(PlainSelect plainSelect){
        FromItem fromItem = plainSelect.getFromItem();
        List<Table> tables = processFromItem(fromItem);
        tables.forEach(item-> System.out.println(item.getName()));
        Expression where = plainSelect.getWhere();
        plainSelect.setWhere(buildExpression(where,tables));
    }

    // 处理FromItem
    private List<Table> processFromItem(FromItem fromItem) {
        List<Table> mainTables = new ArrayList<>();
        if(fromItem instanceof Table){
            Table fromTable = (Table) fromItem;
            mainTables.add(fromTable);
        }
        // 子查询的处理
        else if (fromItem instanceof SubSelect){

        }
        return mainTables;
    }

    // 创建表达式
    private Expression buildExpression(Expression currentExpression,List<Table> tables){
        // From下没有表需要处理
        if(CollectionUtils.isEmpty(tables)){
            return currentExpression;
        }
        // 第一步，获得对应Table的数据权限表达式
        Expression dataPermission = null;
        for(Table table: tables){
            // 构建每个表的权限 Expression 条件
            Expression expression = buildDataPermissionExpression(table);
        }
        return null;
    }

    // 将当前table与数据规则对象当中的表名进行对比
    private Expression buildDataPermissionExpression(Table table){
        // 生成对应的表达式
        Expression allExpression = null;
        List<DataPermissionRule> rules = DataPermissionInterceptorContext.getRules();
        for(DataPermissionRule item :rules){
            if(!item.getTableNames().contains(table.getName())){
                continue;
            }
            Expression permissionExpression = item.getPermissionExpression(table.getName(), table.getAlias());
            if(permissionExpression == null){
                continue;
            }
            allExpression = allExpression == null ? permissionExpression
                    : new AndExpression(allExpression,permissionExpression);
        }
        return allExpression;
    }
}
