package com.zyj.cloud.service;

import com.zyj.cloud.beans.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : zhang yijun
 * @date : 2021/1/19 10:36
 * @description :
 *      通过Feign去服务注册中心查询对应的服务
 */

@Component
@FeignClient(value = "PAYMENT-HYSTRIX-SERVICE")
public interface PaymentFeign {

    /**
     * normal
     * @return
     */
    @GetMapping("/payment/normal")
    String normal();

    /**
     * timeout
     * @return
     */
    @GetMapping("/payment/timeout")
    String timeout();
}
