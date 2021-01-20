package com.zyj.cloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zyj.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return "线程名："+Thread.currentThread().getName();
    }

    @HystrixCommand(fallbackMethod = "getServerPortWhenTimeoutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String getServerPortWhenTimeout() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.info("超时异常");
        }
        return "线程名："+Thread.currentThread().getName();
    }

    public String getServerPortWhenTimeoutHandler(){
        return "接口超时，请稍后重试";
    }
}
