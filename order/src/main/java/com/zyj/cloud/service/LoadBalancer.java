package com.zyj.cloud.service;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author : zhang yijun
 * @date : 2021/1/18 13:51
 * @description : TODO
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> instances);
}
