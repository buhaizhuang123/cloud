package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:19
 * @mark ProductApp
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductApp {


    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class,args);
    }

}
