package com.lengao.eureka.controller;

import com.netflix.eureka.lease.Lease;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/23
 */
@RestController()
@RequestMapping("/consumer")
@Slf4j
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/test/{msg}")
    String test(@PathVariable("msg") String msg) {
        String forObject = restTemplate.getForObject("http://demo-eureka-service/service/test/" + msg, String.class);
        return "provider: " + forObject;
    }

    @GetMapping("/getService")
    String getService() {
        List<String> services = discoveryClient.getServices();
        log.info("服务名称列表：{}",services);

        List<ServiceInstance> instances = discoveryClient.getInstances("demo-eureka-service");
        for (ServiceInstance instance : instances) {
            log.info("====================================");
            log.info("InstanceId===》{}", instance.getInstanceId());
            log.info("ServiceId===》{}", instance.getServiceId());
            log.info("Host===》{}", instance.getHost());
            log.info("Port===》{}", instance.getPort());
            log.info("Secure===》{}", instance.isSecure());
            log.info("Uri===》{}", instance.getUri());
            log.info("Metadata===》{}", instance.getMetadata());
            log.info("Scheme===》{}", instance.getScheme());
        }

        return instances.toString();
    }
}
