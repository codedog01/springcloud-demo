package demoorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lengao.springcloud.dto.OrderDTO;
import com.lengao.springcloud.pojo.Order;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
public interface OrderService extends IService<Order> {
    int addOrder(OrderDTO order);

    int deleteOrder(Integer orderId);

    Order getOrder(Integer orderId);

    int updateOrder(Order order);
}
