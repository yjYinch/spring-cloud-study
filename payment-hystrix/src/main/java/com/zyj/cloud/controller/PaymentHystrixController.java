package com.zyj.cloud.controller;

import com.zyj.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private PaymentHystrixService service;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/normal")
    public String getServerPort(){
        return service.getServerPort();
    }

    @GetMapping("/payment/timeout")
    public String getServerPortWhenTimeout(){
        return service.getServerPortWhenTimeout();
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        log.info("当前id={}", id);
        try {
            return service.paymentCircuitBreaker(id);
        } catch (Exception e) {
            log.info("调用service层的paymentCircuitBreaker方法报错");
            return "error";
        }
    }
}
