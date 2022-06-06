package com.cloud.common;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author haizhuangbu
 * @date 2022/6/5 18:25
 * @mark MybatisIntercepter mybatis 拦截器
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class MybatisInterceptor implements Interceptor {

    private Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

    private final Integer MAPPED_STATEMENT_INDEX = 0;

    private final Integer PARAM = 1;

    // 默认分页
    private final Integer ROW_BOUNDS_INDEX = 2;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("分页拦截器");
        // 1. 方法获取参数
        Object[] args = invocation.getArgs();
        // 分页器
        RowBounds rowBounds = (RowBounds) args[ROW_BOUNDS_INDEX];
        // 检查是否为默认分页
        if (rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }
        // 设置为默认
        args[ROW_BOUNDS_INDEX] = RowBounds.DEFAULT;
        // 执行器
        MappedStatement mappedStatements = (MappedStatement) args[MAPPED_STATEMENT_INDEX];

        // 获取执行sql
        BoundSql boundSql = mappedStatements.getBoundSql(PARAM);

        String sql = boundSql.getSql();

        // 获取数据库类型
        String databaseProductName = mappedStatements.getConfiguration().getEnvironment().getDataSource().getConnection().getMetaData().getDatabaseProductName();

        // mysql 的 分页处理
        if ("MYSQL".equalsIgnoreCase(databaseProductName)) {
            sql = mysql(sql, rowBounds);
        }

        // oracle 的分页处理
        if ("ORACLE".equalsIgnoreCase(databaseProductName)) {
            sql = oracle(sql, rowBounds);
        }

        StaticSqlSource staticSqlSource = new StaticSqlSource(mappedStatements.getConfiguration(), sql, boundSql.getParameterMappings());
        // 通过反射设置MapperStatment 的sqlSource字段
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(mappedStatements, staticSqlSource);

        return invocation.proceed();
    }

    private String oracle(String sql, RowBounds rowBounds) {


        // 为查询语句给到别名
        sql = "select * from (select t1.*, rownum rn from (" + sql +
                ") t1 ) t2 where rn > " + (rowBounds.getOffset() - 1) * rowBounds.getLimit() + " and rn <= " + rowBounds.getOffset() * rowBounds.getLimit();

        return sql;
    }

    private String mysql(String sql, RowBounds rowBounds) {
        // 页数
        int limit = rowBounds.getLimit();

        // 页码
        int offset = rowBounds.getOffset();

        String format = String.format("LIMIT %d,%d", offset, limit);

        if (sql.contains(format)) {
            sql = sql.replaceAll(format, "");
        }

        sql += format;

        return sql;
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
