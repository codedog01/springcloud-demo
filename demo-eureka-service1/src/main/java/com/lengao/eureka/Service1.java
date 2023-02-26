package com.lengao.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>description goes here</p>
 *
 * @date 2023/2/23
 */
@SpringBootApplication
@EnableEurekaClient
public class Service1 {
    public static void main(String[] args) {
        SpringApplication.run(Service1.class);
    }
}
