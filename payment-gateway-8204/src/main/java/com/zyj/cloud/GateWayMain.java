package com.zyj.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : zhang yijun
 * @date : 2021/1/26 10:48
 * @description : TODO
 */

@SpringBootApplication
@EnableEurekaClient
public class GateWayMain {

    public static void main(String[] args) {
        SpringApplication.run(GateWayMain.class, args);
    }
}
