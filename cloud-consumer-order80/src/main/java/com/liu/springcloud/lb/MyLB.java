package com.liu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author root
 * @create 2021-02-03 15:47
 */
@Component
public class MyLB implements LoadBalanced{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 获取点击的次数
     * @return
     */
    private final int getAndIncrement(){
        int m;
        int n; //点击的次数
        do{
            m = this.atomicInteger.get();
            n = m > 2147483647 ? 0 : m+1;
        }while (!this.atomicInteger.compareAndSet(m,n));
        System.out.println("实际访问的次数："+n);
        return n;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int size = serviceInstances.size();
        int n = this.getAndIncrement();
        int index = n % size;
        return serviceInstances.get(index);
    }
}
