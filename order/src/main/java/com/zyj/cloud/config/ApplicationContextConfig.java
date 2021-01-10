package com.zyj.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zhangyijun
 * @date: created in 17:00 2021/1/9
 * @description
 */

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  //负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
