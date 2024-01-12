package com.bu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.bu.*.dao"})
@EnableSwagger2
public class Program {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(Program.class, args);
    }
}