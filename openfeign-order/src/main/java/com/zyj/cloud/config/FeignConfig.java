package com.zyj.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : zhang yijun
 * @date : 2021/1/19 15:45
 * @description : TODO
 */

@Configuration
public class FeignConfig {

    /**
     * 配置日志级别
     * （1）NONE：
     *      No logging.
     * （2）BASIC：
     *      Log only the request method and URL and the response status code and execution time.
     * （3）HEADERS：
     *      Log the basic information along with request and response headers.
     * （4）FULL：
     *      Log the headers, body, and metadata for both requests and responses.
     *
     * @return NONE、BASIC、HEADERS、FULL
     */
    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}

