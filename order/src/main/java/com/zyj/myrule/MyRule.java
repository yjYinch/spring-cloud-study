package com.zyj.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : zhang yijun
 * @date : 2021/1/18 10:59
 * @description : TODO
 */

@Configuration
public class MyRule {

    @Bean
    public IRule getRule(){
        // 定义随机规则
        return new RoundRobinRule();
    }
}
