package com.zyj.cloud.service;

/**
 * @author: zhangyijun
 * @date: created in 22:57 2021/1/19
 * @description
 */
public interface PaymentHystrixService {

     String getServerPort();

     String getServerPortWhenTimeout();

     String paymentCircuitBreaker(Integer id) throws Exception;
}
