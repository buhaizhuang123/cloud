package com.cloud.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/6/12 12:39
 * @mark SqlSession
 */
public class SqlSession {


    private Logger logger = LoggerFactory.getLogger(SqlSession.class);

    private static org.apache.ibatis.session.SqlSession sqlSession;

    static {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsReader("config/mybatis.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSession = sqlSessionFactory.openSession();
    }


    public static <T> T getMapper(Class<T> mapper) {
        return sqlSession.getMapper(mapper);
    }


}
