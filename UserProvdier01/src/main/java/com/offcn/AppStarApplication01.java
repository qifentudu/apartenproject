package com.offcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppStarApplication01 {
    public static void main(String[] args) {
        SpringApplication.run(AppStarApplication01.class,args);
    }
}
