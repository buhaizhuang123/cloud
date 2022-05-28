package com.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    @RequestMapping("/")
    public String defaultPage() {
        return "Hello Default";
    }


}
