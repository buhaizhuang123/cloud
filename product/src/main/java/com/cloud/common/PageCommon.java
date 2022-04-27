package com.cloud.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author haizhuangbu
 * @date 2022/4/27 12:38
 * @mark PageCommon
 */
@Slf4j
@Intercepts(@Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class,RowBounds.class, ResultHandler.class}))
public class PageCommon implements Interceptor {

    private final Integer MAPPED_STATEMENT_INDEX = 0;

    private final Integer PARAMETER_INDEX = 1;

    private final Integer ROW_ROUNDS_INDEX = 2;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("------------- Mybatis 分页开始 ---------------");
        Object[] params = invocation.getArgs();
        // 分页参数
        RowBounds rb = (RowBounds)params[ROW_ROUNDS_INDEX];
        if (rb == RowBounds.DEFAULT) {
            return invocation.proceed();
        }
        params[ROW_ROUNDS_INDEX] = RowBounds.DEFAULT;

        MappedStatement ms =   (MappedStatement)params[MAPPED_STATEMENT_INDEX];

        BoundSql boundSql = ms.getBoundSql(params[PARAMETER_INDEX]);

        String sql = boundSql.getSql();
        System.out.println("sql = " + sql);
        String databaseProductName = ms.getConfiguration().getEnvironment().getDataSource().getConnection().getMetaData().getDatabaseProductName();
        System.out.println("databaseProductName = " + databaseProductName);

        if ("mysql".equalsIgnoreCase(databaseProductName)) {
            sql = mysql(sql,rb);
        }else if ("oracle".equalsIgnoreCase(databaseProductName)){
            sql = oracle(sql,rb);
        }
        System.out.println("组装后的sql"+sql);

        StaticSqlSource staticSqlSource = new StaticSqlSource(ms.getConfiguration(), sql, boundSql.getParameterMappings());
        // 通过反射设置MapperStatment 的sqlSource字段
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(ms, staticSqlSource);
        return invocation.proceed();
    }

    private String oracle(String sql, RowBounds rb) {
        sql = "select * from (select t1.*, rownum rn from (" + sql +
                ") t1 ) t2 where rn > " + (rb.getOffset() - 1) * rb.getLimit() + " and rn <= " + rb.getOffset() * rb.getLimit();
        return sql;
    }

    private String mysql(String sql, RowBounds rb) {
        String limit = String.format(" LIMIT %d,%d",rb.getOffset(),rb.getLimit());
        System.out.println("sql.contains(limit) = " + sql.contains(limit));
        if (sql.contains(limit)){
           sql =  sql.replaceAll(limit,"");
        }
        return sql + limit;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
