package com.zyj.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhangyijun
 * @date: created in 22:56 2021/1/19
 * @description
 */
@Slf4j
@RestController
public class PaymentHystrixController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/normal")
    public String getServerPort(){
        return "线程名："+Thread.currentThread().getName();
    }

    @GetMapping("/payment/timeout")
    public String getServerPortWhenTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.info("超时异常");
        }
        return "线程名："+Thread.currentThread().getName();
    }
}
