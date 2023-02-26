package com.lengao.springcloud.demogoods.controller;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

import static com.alibaba.nacos.api.common.Constants.DEFAULT_GROUP;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/24
 */
@RestController
@Api("Nacos测试")
@RequestMapping("nacos")
@Slf4j
public class HelloNacosController {

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @NacosInjected
    ConfigService configService;

    {
        ConfigService configService1 = nacosConfigManager.getConfigService();
        configService = configService1;
    }

    private final String DATA_ID = "service-goods.yaml";

    @ApiOperation("发布配置")
    @GetMapping("testPublishConfig")
    public void testPublishConfig() throws NacosException {
        ConfigService configService = nacosConfigManager.getConfigService();
        boolean success = configService.publishConfig(DATA_ID, DEFAULT_GROUP, "spring.application.name=app", "properties");
        log.info("是否发布成功：{}", success);
    }

    @ApiOperation("获取配置")
    @GetMapping("testGetConfig")
    public void testGetConfig() throws NacosException {
        ConfigService configService = nacosConfigManager.getConfigService();
        String config = configService.getConfig(DATA_ID, DEFAULT_GROUP, 3000);
        log.info("配置信息为：{}", config);
    }

    @ApiOperation("监听配置")
    @GetMapping("testGetConfig")
    public void testLicenseConfig() throws NacosException {
        ConfigService configService = nacosConfigManager.getConfigService();
        configService.addListener(DATA_ID, DEFAULT_GROUP, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("更新后的配置信息为：{}", configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }


    @NacosConfigListener(dataId = DATA_ID, groupId = DEFAULT_GROUP)
    public void onMessage(String config) {
        log.info("更新后的配置信息为：{}", config);
    }

    @ApiOperation("移除监听")
    @GetMapping("testGetConfig")
    public void testRemoveLicenseConfig() throws NacosException {
        ConfigService configService = nacosConfigManager.getConfigService();
        configService.removeListener(DATA_ID, DEFAULT_GROUP, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("更新后的配置信息为：{}", configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }


}
