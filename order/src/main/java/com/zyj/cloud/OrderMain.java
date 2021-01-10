package com.zyj.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: zhangyijun
 * @date: created in 16:30 2021/1/9
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class, args);
    }
}
