package com.bu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author haizhuangbu
 * @date 2022/7/12 10:00
 * @mark ProviderApp
 */
@SpringBootApplication
/**nacos*/
@EnableDiscoveryClient
/**hystrix*/
@EnableCircuitBreaker
@EnableFeignClients
public class ProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class);
    }

}
