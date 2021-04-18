package com.liu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author root
 * @create 2021-02-09 14:44
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_timeout , (┬＿┬)";
    }
}
