package com.zyj.cloud.service.impl;

import com.zyj.cloud.service.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zhang yijun
 * @date : 2021/1/18 13:52
 * @description : TODO
 */

@Service
@Slf4j
public class LoadBalancerImpl implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int incrementAndGet() {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current + 1;
        } while (!atomicInteger.compareAndSet(current, next));
        log.info("访问次数next: {}", next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> instances) {
        if (instances == null || instances.size() == 0){
            log.warn("instances is not null, make sure ServiceInstance exists");
            return null;
        }
        // 获取索引下标
        int index = incrementAndGet() % instances.size();
        return instances.get(index);
    }
}
