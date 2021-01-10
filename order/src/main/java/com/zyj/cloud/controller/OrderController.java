package com.zyj.cloud.controller;

import com.zyj.cloud.beans.PaymentBean;
import com.zyj.cloud.beans.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: zhangyijun
 * @date: created in 16:55 2021/1/9
 * @description
 */

@RestController
@Slf4j
public class OrderController {

    /**
     * 对外暴露的微服务名称
     */
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/order/payment/insert")
    public Result insertPayment(@RequestBody PaymentBean paymentBean){
        log.info("接收到数据：{}", paymentBean);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/insert", paymentBean, Result.class);
    }
    @GetMapping("/order/payment/get")
    public Result getPayment(@RequestParam(value = "id") Integer id){
        log.info("接收到序列号id: {}", id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, Result.class);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("微服务名称：{}", service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                log.info("服务id:{}, host:{}, port:{}, uri:{}", instance.getInstanceId(), instance.getHost(), instance.getPort(), instance.getUri());
            }
        }
        return services;
    }
}
