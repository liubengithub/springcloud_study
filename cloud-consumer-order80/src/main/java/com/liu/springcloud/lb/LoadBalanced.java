package com.liu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author root
 * @create 2021-02-03 15:44
 */
public interface LoadBalanced {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
