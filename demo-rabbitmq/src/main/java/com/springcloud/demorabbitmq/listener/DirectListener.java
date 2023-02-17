package com.springcloud.demorabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.springcloud.demorabbitmq.config.DirectRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/13
 */
@Slf4j
@Component
public class DirectListener {

    private final LongAdder consFailCount = new LongAdder();
    private final LongAdder retryCount = new LongAdder();

    @RabbitListener(queues = DirectRabbitConfig.DIRECT_QUEUE_DIRECT_A)
    public void queueA(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        try {
            String msgBody = new String(msg.getBody());
            if ("error".equals(msgBody)) {
                throw new IOException("手动抛出错误测试");
            }
            channel.basicAck(deliveryTag, true);
            log.info("接收到来自 {} 的消息{}", DirectRabbitConfig.DIRECT_QUEUE_DIRECT_A, new String(msg.getBody()));
        } catch (IOException e) {
            try {
                log.error("发生异常，重新放入队列，失败原因:{}", e.getMessage());
                channel.basicNack(deliveryTag, false, true);
                consFailCount.increment();
                if (consFailCount.intValue() >= 6) {
                    log.error("多次消费失败，放弃消息");
                    //拒绝消息,为true会重新放回队列（自定义拒绝规则，比如：redis计数，消费5次后依然报错失败则拒绝）
                    channel.basicReject(deliveryTag, false);
                }
            } catch (IOException ioException) {
                log.error("重新放入队列失败，失败原因:{}", e.getMessage());
                retryCount.increment();
                if (retryCount.intValue() >= 6) {
                    //拒绝消息,为true会重新放回队列（自定义拒绝规则，比如：redis计数，消费5次后依然报错失败则拒绝）
                    channel.basicReject(deliveryTag, false);
                }
            }

//            if (consFailCount.intValue() >= 5) {
//                log.error("消息重试次数达到上限,丢弃消息");
//                consFailCount.reset();
//                channel.basicReject(deliveryTag, false);
//            }
//            log.error("消费者出错,mq参数:{}，错误信息：{}", msg, e.getMessage());
        }
        log.info("retryCount===>{}", retryCount);
        log.info("consFailCount===>{}", consFailCount);
    }

    @RabbitListener(queues = DirectRabbitConfig.DIRECT_QUEUE_DIRECT_B)
    public void queueB(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, true);
            log.info("1接收到来自 {} 的消息{}", DirectRabbitConfig.DIRECT_QUEUE_DIRECT_B, new String(msg.getBody()));
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


    @RabbitListener(queues = DirectRabbitConfig.DIRECT_QUEUE_DIRECT_B)
    public void queueB2(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, true);
            log.info("2接收到来自 {} 的消息{}", DirectRabbitConfig.DIRECT_QUEUE_DIRECT_B, new String(msg.getBody()));
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
