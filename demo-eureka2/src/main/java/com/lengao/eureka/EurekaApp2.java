package com.lengao.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>description goes here</p>
 *
 * @author ���
 * @date 2023/2/23
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApp2 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApp2.class);
    }
}
