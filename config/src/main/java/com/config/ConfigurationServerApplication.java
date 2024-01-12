package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author haizhuangbu
 * @date 2022/10/14 11:19
 * @mark ConfigurationServerApplication
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigurationServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServerApplication.class, args);
    }


}
