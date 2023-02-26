package com.lengao.springcloud.demogoods.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.lengao.springcloud.demogoods.utils.SnowflakeIdWorker;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Configuration
public class BeanConfig {


    IRule iRule(){
        return new NacosRule();
    }

//    /**
//     * 自定义主键生成策略 举报原因
//     * */
//    @Bean
//    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
//        return plusProperties -> plusProperties.getGlobalConfig().setIdentifierGenerator(new CustomIdGenerator());
//    }
//
//
//    public class CustomIdGenerator implements IdentifierGenerator {
//        @Override
//        public Long nextId(Object entity) {
//            //返回生成的id值即可.
//            return SnowflakeIdWorker.generateId();
//        }
//    }
}
