package demoorder.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Configuration
public class OrderMQConfig {
    // 订单创建
    public static final String ORDER_CREATE = "order.create";
    // 死信队列：取消订单（订单超时）
    public static final String ORDER_CANCEL = "order.cancel";
    // 处理
    public static final String ORDER_DEAL = "order.deal";


}
