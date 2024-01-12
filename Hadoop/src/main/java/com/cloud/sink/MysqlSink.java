package com.cloud.sink;

import com.cloud.common.SqlSession;
import com.cloud.sys.dao.WorldCountMapper;
import com.cloud.vo.GlobalConfig;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author haizhuangbu
 * @date 2022/6/11 16:48
 * @mark MysqlSink
 */
public class MysqlSink extends RichSinkFunction<Tuple2<String, Integer>> {

    private Connection connection;

    private PreparedStatement preparedStatement;

    @Override
    public void open(Configuration parameters) throws Exception {

        super.open(parameters);
//
//        Class.forName(GlobalConfig.DRIVER_CLASS);
//
//        connection = DriverManager.getConnection(GlobalConfig.DB_URL, GlobalConfig.USER_NAME, GlobalConfig.PASSWORD);
//
    }


    @Override
    public void close() throws Exception {
        super.close();

//        if (preparedStatement != null) {
//            preparedStatement.close();
//        }
//
//        if (connection != null) {
//            connection.close();
//        }
//
//        super.close();
    }


    @Override
    public void invoke(Tuple2<String, Integer> value, Context context) throws Exception {
        WorldCountMapper worldCount = SqlSession.getMapper(WorldCountMapper.class);
        Integer count = worldCount.findCountByName(value.f0);
        if (count > 0) {
            worldCount.updateByName(value.f0, value.f1);
        } else {
            worldCount.insert(value.f0, value.f1);
        }


    }

}
