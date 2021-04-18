package com.liu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author root
 * @create 2021-02-02 15:20
 */
@RestController
@Slf4j
public class OrderZkController {
    public static final String INVOME_URL = "http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/payment/zk")
    public String payment(){
        return restTemplate.getForObject(INVOME_URL+"/payment/zk",String.class);
    }
}
