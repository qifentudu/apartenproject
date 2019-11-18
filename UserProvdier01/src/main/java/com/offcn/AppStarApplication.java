package com.offcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppStarApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppStarApplication.class,args);
    }
}
