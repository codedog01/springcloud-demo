package com.springcloud.demorabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/13
 */
@Configuration
public class DirectRabbitConfig {
    public final static String DIRECT_QUEUE_DIRECT_A = "DIRECT_A";
    public final static String DIRECT_QUEUE_DIRECT_B = "DIRECT_B";

    public final static String EXCHANGE_DIRECT = "direct_exchange";

    public final static String DIRECT_QUEUE_ROUTING_A = "direct_queue_routing_a";
    public final static String DIRECT_QUEUE_ROUTING_B = "direct_queue_routing_b";

    @Bean
    Queue directQueueA() {
        return new Queue(DIRECT_QUEUE_DIRECT_A, false, false, true);
    }

    @Bean
    Queue directQueueB() {
        return new Queue(DIRECT_QUEUE_DIRECT_B, false, false, true);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT, false, true);
    }

    @Bean
    Binding directBinding1(Queue directQueueA, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueA).to(directExchange).with(DIRECT_QUEUE_ROUTING_A);
    }
    @Bean
    Binding directBinding2(Queue directQueueB, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueB).to(directExchange).with(DIRECT_QUEUE_ROUTING_B);
    }
}
