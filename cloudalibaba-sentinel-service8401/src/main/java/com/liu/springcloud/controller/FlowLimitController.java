package com.liu.springcloud.controller;

import cn.hutool.core.thread.ThreadUtil;
import io.micrometer.core.instrument.util.TimeUtils;
import org.apache.commons.lang3.ThreadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author root
 * @create 2021-04-17 21:24
 */
@RestController
public class FlowLimitController {
    @RequestMapping("/testA")
    public String testA() throws InterruptedException {
       // TimeUnit.MILLISECONDS.sleep(800);
       return "testA";
    }
    @RequestMapping("/testB")
    public String testB(){
        return "testB";
    }
}
