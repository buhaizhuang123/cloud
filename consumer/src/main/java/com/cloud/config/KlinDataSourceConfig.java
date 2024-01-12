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
import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/10/26 19:59
 * @mark DataSourceConfig
 */
@Configuration
@MapperScan(value = {"com/cloud/dict/mapper"}, sqlSessionFactoryRef = "klinSqlSessionFactory")
public class KlinDataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.klin")
    DataSource klin() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    SqlSessionFactory klinSqlSessionFactory(@Qualifier("klin") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/klin/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    SqlSessionTemplate klinSqlSessionTemplate(@Qualifier("klinSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
