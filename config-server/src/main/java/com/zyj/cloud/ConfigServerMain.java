package com.zyj.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : zhang yijun
 * @date : 2021/1/27 17:38
 * @description : TODO
 */

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServerMain {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerMain.class, args);
    }
}
