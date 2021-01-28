package com.zyj.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhang yijun
 * @date : 2021/1/27 11:02
 * @description : TODO
 */
@RestController
@RefreshScope
public class ConfigClientController {
    
    @Value("${config.test}")
    private String configTest;

    @GetMapping("/get/config")
    public String getConfigTest(){
        return configTest;
    }
}
