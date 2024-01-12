package com.cloud;


import com.cloud.common.MybatisInterceptor;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author haizhuangbu
 * @date 2022/4/27 10:40
 * @mark ConsumerApp
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = {"com.cloud.sys.dao","com.cloud.person.dao","com.cloud.batch.dao"})
//@EnableHystrix
@EnableTransactionManagement
public class ConsumerApp {

    public static void main(String[] args) {

        SpringApplication.run(ConsumerApp.class, args);
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

//    @Bean
//    public IRule iRule() {
//        // 轮询
//        return new RoundRobinRule();
//        // 配置随机负载均衡
////        return new RandomRule();
//    }

    @Bean
    public RedissonClient redisson(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }


}
