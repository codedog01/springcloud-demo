package com.lengao.springcloud.demogoods;

import com.lengao.springcloud.demogoods.config.FeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FeignConfig.class)
public class GoodsApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication1.class, args);
    }
}
