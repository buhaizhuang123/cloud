package com.cloud;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.cloud.common.PageCommon;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:19
 * @mark ProductApp
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.cloud.shop.shop", "com.cloud.usr.dao"})
@EnableCircuitBreaker
@RequestMapping
public class ProductApp {


    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class, args);
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.addInterceptor(new PageCommon());
            }
        };
    }

}
