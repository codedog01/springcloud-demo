package com.lengao.eureka.config;

import com.netflix.ribbon.Ribbon;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/23
 */
public class BeanConfig {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
