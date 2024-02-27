package com.ocean.core;

import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import net.sf.jsqlparser.JSQLParserException;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataPermissionInterceptor extends JsqlParserSupport implements InnerInterceptor {
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        String sql =  boundSql.getSql();
        System.out.println(sql);
        parserSingle(sql,null);
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
        }
    }

    // 处理原生Select语句
    private void processPlainSelect(PlainSelect plainSelect){
        FromItem fromItem = plainSelect.getFromItem();
        List<Table> tables = processFromItem(fromItem);
        tables.forEach(item-> System.out.println(item.getName()));
    }

    // 处理FromItem
    private List<Table> processFromItem(FromItem fromItem) {
        List<Table> mainTables = new ArrayList<>();
        if(fromItem instanceof Table){
            Table fromTable = (Table) fromItem;
            mainTables.add(fromTable);
        }
        // 子连接
        else if (fromItem instanceof SubJoin){

        }
        return mainTables;
    }
}
