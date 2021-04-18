package com.liu.springcloud.controller;


import com.liu.springcloud.entities.CommonResult;
import com.liu.springcloud.entities.Payment;
import com.liu.springcloud.lb.LoadBalanced;
import com.liu.springcloud.lb.MyLB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author root
 * @create 2021-01-31 12:09
 */
@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalanced loadBalanced;
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("查询OK");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/entity/create")
    public CommonResult<Payment> create1(Payment payment){
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if(commonResultResponseEntity.getStatusCode().is2xxSuccessful()){
            return commonResultResponseEntity.getBody();
        }else {
            return new CommonResult<>(444,"创建失败");
        }


    }
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment1(@PathVariable("id") Long id){
        log.info("查询OK");
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(commonResultResponseEntity.getStatusCode().is2xxSuccessful()){
            return commonResultResponseEntity.getBody();
        }else {
            return new CommonResult<>(444,"查询失败");
        }
    }
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances!=null && instances.size()>0){
            ServiceInstance instance = loadBalanced.instances(instances);
            String url = "http://"+instance.getHost()+":"+instance.getPort();
            System.out.println(url);
            return restTemplate.getForObject(instance.getUri()+"/payment/lb",String.class);
        }else {
            return null;
        }
    }
    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }

}
