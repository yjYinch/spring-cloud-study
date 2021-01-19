package com.zyj.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zhangyijun
 * @date: created in 23:25 2021/1/19
 * @description
 */
@SpringBootApplication
@EnableFeignClients
public class OrderHystrixMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain.class, args);
    }
}
