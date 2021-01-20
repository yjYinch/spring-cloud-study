package com.zyj.cloud.service;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhangyijun
 * @date: created in 22:57 2021/1/19
 * @description
 */
public interface PaymentHystrixService {

     String getServerPort();

     String getServerPortWhenTimeout();
}
