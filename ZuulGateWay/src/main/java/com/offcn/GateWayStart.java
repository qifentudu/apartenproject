package com.offcn;

import com.offcn.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/*@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker*/
//@SpringCloudApplication//相当于上面上3个注解
@SpringBootApplication
@EnableZuulProxy
@EnableHystrix
public class GateWayStart {
    public static void main(String[] args) {
        SpringApplication.run(GateWayStart.class,args);
    }
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
