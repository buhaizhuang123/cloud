package com.cloud;


import com.cloud.common.MybatisInterceptor;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2022/4/27 10:40
 * @mark ConsumerApp
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
@MapperScan(basePackages = "com.cloud.sys.dao")
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    @RequestMapping("/")
    public String defaultPage() {
        return "Hello Default";
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.addInterceptor(new MybatisInterceptor());
            }
        };
    }


}
