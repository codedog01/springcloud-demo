package com.lengao.springcloud.demogoods;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author LengAo
 * @date 2023/2/27
 */
public class CustomFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
    }
}
