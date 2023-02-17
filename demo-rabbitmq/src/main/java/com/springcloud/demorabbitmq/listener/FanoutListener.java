package com.springcloud.demorabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.springcloud.demorabbitmq.config.DirectRabbitConfig;
import com.springcloud.demorabbitmq.config.FanoutRabbitConfig;
import com.springcloud.demorabbitmq.config.TopicRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/13
 */
@Slf4j
@Component
public class FanoutListener {

    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_NAME_A)
    public void queueA(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, true);
            log.info("接收到来自 {} 的消息{}", DirectRabbitConfig.DIRECT_QUEUE_DIRECT_A, new String(msg.getBody()));
        } catch (IOException e) {
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                log.error("重新放入队列失败，失败原因:{}", e.getMessage(), e);
            }
            log.error("TopicConsumer消费者出错,mq参数:{}，错误信息：{}", msg, e.getMessage(), e);

            //拒绝消息,为true会重新放回队列（自定义拒绝规则，比如：redis计数，消费5次后依然报错失败则拒绝）
            channel.basicReject(deliveryTag, false);
        }
    }
    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_NAME_B)
    public void queueB(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, true);
            log.info("接收到来自 {} 的消息{}", FanoutRabbitConfig.FANOUT_QUEUE_NAME_B, new String(msg.getBody()));
        } catch (IOException e) {
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                log.error("重新放入队列失败，失败原因:{}", e.getMessage(), e);
            }
            log.error("TopicConsumer消费者出错,mq参数:{}，错误信息：{}", msg, e.getMessage(), e);

            //拒绝消息,为true会重新放回队列（自定义拒绝规则，比如：redis计数，消费5次后依然报错失败则拒绝）
            channel.basicReject(deliveryTag, false);
        }
    }
    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_QUEUE_NAME_C)
    public void queueC(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, true);
            log.info("接收到来自 {} 的消息{}", FanoutRabbitConfig.FANOUT_QUEUE_NAME_C, new String(msg.getBody()));
        } catch (IOException e) {
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                log.error("重新放入队列失败，失败原因:{}", e.getMessage(), e);
            }
            log.error("TopicConsumer消费者出错,mq参数:{}，错误信息：{}", msg, e.getMessage(), e);

            //拒绝消息,为true会重新放回队列（自定义拒绝规则，比如：redis计数，消费5次后依然报错失败则拒绝）
            channel.basicReject(deliveryTag, false);
        }
    }
}
