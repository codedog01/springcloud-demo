package com.springcloud.demorabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.springcloud.demorabbitmq.config.TopicRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/13
 */
@Slf4j
@Component
public class TopicListener {

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = TopicRabbitConfig.QUEUE_GOODS_A, durable = "true"),
//            exchange = @Exchange(name = TopicRabbitConfig.TOPIC_EXCHANGE_GOODS, type = "topic"),//交换机名称，durable指是否持久化到数据库，type：模式
//            key = TopicRabbitConfig.ROUTING_KEY_GOODS_A //路由匹配规则
//    ))

    /**
     * 消费者监听接口
     *
     * @param msg     Message消息
     * @param channel mq channel用于设置手动模式
     *                channel.basicAck(deliverTag, true);    	消费成功，确认消息
     *                channel.basicNack(deliverTag, false, true);    nack返回false，出现异常并重新回到队列，重新消费
     *                channel.basicReject(deliverTag, false);        为false则拒绝消息，丢掉该消息；为true会重新放回队列，重新消费
     * @throws IOException
     */
    @RabbitListener(queues = TopicRabbitConfig.QUEUE_B)
    public void queueA(Message msg, Channel channel) throws IOException {

        Map<String, Object> message = msg.getMessageProperties().getHeader("param");
        long deliverTag = msg.getMessageProperties().getDeliveryTag();
        try {
            //业务逻辑代码...
            log.info("接收到来自 {} 的消息{}",TopicRabbitConfig.QUEUE_B,new String(msg.getBody()));
            //消费成功，确认消息
            channel.basicAck(deliverTag, true);
        } catch (Exception e) {
            try {
                //nack返回false，并重新回到队列
                channel.basicNack(deliverTag, false, true);
            } catch (IOException ioException) {
                log.error("重新放入队列失败，失败原因:{}", e.getMessage(), e);
            }
            log.error("TopicConsumer消费者出错,mq参数:{}，错误信息：{}", message, e.getMessage(), e);

            //拒绝消息,为true会重新放回队列（自定义拒绝规则，比如：redis计数，消费5次后依然报错失败则拒绝）
            channel.basicReject(deliverTag, false);
        }
    }


    @RabbitListener(queues = TopicRabbitConfig.QUEUE_C)
    public void queueB(Message msg, Channel channel) throws IOException {
        Map<String, Object> message = msg.getMessageProperties().getHeader("param");
        long deliverTag = msg.getMessageProperties().getDeliveryTag();
        try {
            //业务逻辑代码...
            log.info("接收到来自 {} 的消息{}",TopicRabbitConfig.QUEUE_C, new String(msg.getBody()));

            //消费成功，确认消息
            channel.basicAck(deliverTag, true);
        } catch (Exception e) {
            try {
                //nack返回false，并重新回到队列
                channel.basicNack(deliverTag, false, true);
            } catch (IOException ioException) {
                log.error("重新放入队列失败，失败原因:{}", e.getMessage(), e);
            }
            log.error("TopicConsumer消费者出错,mq参数:{}，错误信息：{}", message, e.getMessage(), e);

            //拒绝消息,为true会重新放回队列（自定义拒绝规则，比如：redis计数，消费5次后依然报错失败则拒绝）
            channel.basicReject(deliverTag, false);
        }
    }

    @RabbitListener(queues = TopicRabbitConfig.QUEUE_A)
    public void queueUser(Message msg, Channel channel) throws IOException {
        Map<String, Object> message = msg.getMessageProperties().getHeader("param");
        long deliverTag = msg.getMessageProperties().getDeliveryTag();
        try {
            //业务逻辑代码...
            log.info("接收到来自 {} 的消息{}",TopicRabbitConfig.QUEUE_A, new String(msg.getBody()));
            //消费成功，确认消息
            channel.basicAck(deliverTag, true);
        } catch (Exception e) {
            try {
                //nack返回false，并重新回到队列
                channel.basicNack(deliverTag, false, true);
            } catch (IOException ioException) {
                log.error("重新放入队列失败，失败原因:{}", e.getMessage(), e);
            }
            log.error("TopicConsumer消费者出错,mq参数:{}，错误信息：{}", message, e.getMessage(), e);

            //拒绝消息,为true会重新放回队列（自定义拒绝规则，比如：redis计数，消费5次后依然报错失败则拒绝）
            channel.basicReject(deliverTag, false);
        }
    }
}
