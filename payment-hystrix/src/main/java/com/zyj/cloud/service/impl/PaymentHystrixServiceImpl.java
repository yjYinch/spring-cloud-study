package com.zyj.cloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zyj.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhang yijun
 * @date : 2021/1/20 14:28
 * @description : TODO
 */

@Service
@Slf4j
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Override
    public String getServerPort() {
        return "线程名：" + Thread.currentThread().getName();
    }

    @HystrixCommand(fallbackMethod = "getServerPortWhenTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String getServerPortWhenTimeout() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.info("超时异常");
        }
        return "线程名：" + Thread.currentThread().getName();
    }

    @HystrixCommand(fallbackMethod = "getServerPortWhenTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 错误百分比
            // 在时间窗口期10s内，如果10次请求次数有60%的请求都失败了，则开启断路器
    })
    @Override
    public String paymentCircuitBreaker(Integer id) throws Exception {
        if (id <= 0) {
            throw new Exception("id小于0");
        }
        String serialNum = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNum;
    }

    public String getServerPortWhenTimeoutHandler() {
        return "接口超时，请稍后重试";
    }

    /**
     * 这个fallback方法要与paymentCircuitBreaker方法的参数对应
     * @param id
     * @return
     */
    public String paymentCircuitBreakerHandler(Integer id) {
        return "系统异常...." + id;
    }

    public String getServerPortWhenTimeoutHandler(Integer id) {
        return "接口超时，请稍后重试";
    }
}
