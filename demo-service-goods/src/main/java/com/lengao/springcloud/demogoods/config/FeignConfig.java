package com.lengao.springcloud.demogoods.config;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/24
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }
}
