package com.springcloud.demorabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/13
 */
@Configuration
public class FanoutRabbitConfig {
    public static final String FANOUT_QUEUE_NAME_A = "fanout_A";
    public static final String FANOUT_QUEUE_NAME_B = "fanout_B";
    public static final String FANOUT_QUEUE_NAME_C = "fanout_C";

    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";


    @Bean
    Queue queueA() {
        return new Queue(FANOUT_QUEUE_NAME_A, false, false, true);
    }
    @Bean
    Queue queueB() {
        return new Queue(FANOUT_QUEUE_NAME_B, false, false, true);
    }
    @Bean
    Queue queueC() {
        return new Queue(FANOUT_QUEUE_NAME_C, false, false, true);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME, false, true);
    }

    @Bean
    Binding bindingA(Queue queueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }
    @Bean
    Binding bindingB(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }
    @Bean
    Binding bindingC(Queue queueC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueC).to(fanoutExchange);
    }
}
