package com.salesmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Salesmanage8381 {
    public static void main(String[] args) {
        SpringApplication.run(Salesmanage8381.class,args);
    }
}

