package com.zyj.cloud.controller;

import com.zyj.cloud.beans.Result;
import com.zyj.cloud.service.PaymentFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhang yijun
 * @date : 2021/1/19 10:53
 * @description : TODO
 */

@RestController
@Slf4j
public class OrderOpenFeignController {

    @Autowired
    private PaymentFeign paymentFeign;

    @GetMapping("/order/payment/get")
    public Result getPayment(@RequestParam(value = "id") Integer id){
        log.info("接收到序列号id: {}", id);
        return paymentFeign.getPayment(id);
    }

    @GetMapping("/order/payment/timeout/get")
    public String getPaymentTimeout(@RequestParam(value = "id") Integer id){
        log.info("接收到序列号id: {}", id);
        // openfeign-ribbon, 客户端默认等待1s
        return paymentFeign.getServerPortWhenTimeout();
    }
}
