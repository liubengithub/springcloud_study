package com.liu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置了一个id为path_rote的路由规则
 * 当访问地址http://localhost:9527/guonei时会自动转发到地址：http://www.baidu.com/guonei
 * /pc/weather断言
 * @author root
 * @create 2021-02-10 15:34
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_rote",r ->r.path("/pc/weather").uri("http://tianqi.sogou.com/pc/weather")).build();
        return routes.build();
    }
}
