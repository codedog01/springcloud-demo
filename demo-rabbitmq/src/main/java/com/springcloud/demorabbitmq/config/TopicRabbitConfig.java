package com.springcloud.demorabbitmq.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/13
 */

@Configuration
public class TopicRabbitConfig {
    //绑定键
    public final static String ROUTING_KEY_A_ALL = "TOPIC.A.*";

    //绑定键
    public final static String ROUTING_KEY_B_ALL = "TOPIC.B.*";


    // 队列名称
    public final static String QUEUE_A = "TOPIC.A.QUEUE_A";
    public final static String QUEUE_B = "TOPIC.B.QUEUE_A";
    public final static String QUEUE_C = "TOPIC.B.QUEUE_B";

    //交换机名称
    public final static String TOPIC_EXCHANGE = "topic_exchange";


    @Bean
    public Queue queueUser() {
        return new Queue(QUEUE_A,true,false,true);
    }

    @Bean
    public Queue queueGoodsA() {
        return new Queue(QUEUE_B,true,false,true);
    }
    @Bean
    public Queue queueGoodsB() {
        return new Queue(QUEUE_C,true,false,true);
    }

    @Bean
    TopicExchange exchangeGoods() {
        return new TopicExchange(TOPIC_EXCHANGE,false,true);
    }

    @Bean
    Binding bindingExchangeMessageUser(Queue queueUser, TopicExchange exchangeGoods) {
        return BindingBuilder.bind(queueUser).to(exchangeGoods).with(ROUTING_KEY_B_ALL);
    }

    @Bean
    Binding bindingExchangeMessageA(Queue queueGoodsA, TopicExchange exchangeGoods) {
        return BindingBuilder.bind(queueGoodsA).to(exchangeGoods).with(ROUTING_KEY_A_ALL);
    }

    @Bean
    Binding bindingExchangeMessageB(Queue queueGoodsB, TopicExchange exchangeGoods) {
        return BindingBuilder.bind(queueGoodsB).to(exchangeGoods).with(ROUTING_KEY_A_ALL);
    }

}
