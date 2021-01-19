package com.zyj.cloud.controller;

import com.zyj.cloud.beans.Result;
import com.zyj.cloud.service.PaymentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangyijun
 * @date: created in 23:26 2021/1/19
 * @description
 */
@RestController
public class OrderHystrixController {

    @Autowired
    private PaymentFeign paymentFeign;

    @GetMapping("/hystrix/normal/get")
    public Result getWhenNormal(){
        String normal = paymentFeign.normal();
        return Result.success(normal);
    }

    @GetMapping("/hystrix/timeout/get")
    public Result getWhenTimeout(){
        String timeout = paymentFeign.timeout();
        return Result.success(timeout);
    }
}
