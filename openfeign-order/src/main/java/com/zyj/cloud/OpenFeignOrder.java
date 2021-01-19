package com.zyj.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : zhang yijun
 * @date : 2021/1/19 10:33
 * @description : TODO
 */

@SpringBootApplication
@EnableFeignClients
public class OpenFeignOrder {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignOrder.class, args);
    }
}
