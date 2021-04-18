package com.liu.springcloud.service;

import com.liu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author root
 * @create 2021-01-30 20:17
 */
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
