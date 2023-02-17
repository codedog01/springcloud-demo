package com.springcloud.demorabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_TEXT_PLAIN;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/13
 */
@Component
@Slf4j
public class RabbitService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMsg(String exchange, String routingKey, String msg) {
        //修改执行相应业务逻辑
        Map<String, Object> param = new HashMap<>();
        param.put("messageId", UUID.randomUUID().toString());
        param.put("createTime", new Date());
        param.put("message", "");
        param.put("Data", "");

        //常规写法
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId(UUID.randomUUID().toString());
        messageProperties.setContentType(CONTENT_TYPE_TEXT_PLAIN);
        messageProperties.setContentEncoding("utf8");
        messageProperties.setDeliveryTag(System.currentTimeMillis());
        messageProperties.setHeader("param", param);
        Message message = new Message(msg.getBytes(), messageProperties);

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("发送消息{}", msg);

    }
}
