package com.cloud.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author haizhuangbu
 * @date 2022/10/26 19:59
 * @mark DataSourceConfig
 */
@Configuration
// 扫描接口路径
@MapperScan(value = {"com/cloud/sys/dao", "com/cloud/person/dao", "com/cloud/batch/dao"}, sqlSessionFactoryRef = "firstSqlSessionFactory")
public class FirstDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.first")
    @Primary
    DataSource first() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    SqlSessionFactory firstSqlSessionFactory(@Qualifier("first") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 指定多数据源 mapper 文件扫描路径,类似yml文件中 mapper-locations: classpath:/mapper/*.xml
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/first/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
