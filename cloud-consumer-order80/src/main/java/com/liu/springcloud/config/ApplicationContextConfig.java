package com.liu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author root
 * @create 2021-01-31 12:17
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
   // @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
