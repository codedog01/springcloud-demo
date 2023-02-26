package demoorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lengao.springcloud.dto.OrderDTO;
import com.lengao.springcloud.pojo.Order;
import com.lengao.springcloud.pojo.OrderDetail;
import demoorder.mapper.OrderDetailMapper;
import demoorder.mapper.OrderMapper;
import demoorder.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;


    @Override
    @Transactional
    public int addOrder(OrderDTO orderDTO) {
        Order order = new Order();
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(orderDTO, order);
        BeanUtils.copyProperties(orderDTO, orderDetail);

        int insert = orderMapper.insert(order);
        orderDTO.getGoodsList().forEach(item -> {
            item.setOrderId(order.getId());
            orderDetailMapper.insert(item);
        });

        return insert;
    }

    @Override
    public int deleteOrder(Integer orderId) {
        return orderMapper.deleteById(orderId);
    }

    @Override
    public Order getOrder(Integer orderId) {
        return orderMapper.selectById(orderId);
    }

    @Override
    public int updateOrder(Order order) {
        orderMapper.updateById(order);
        return 0;
    }

}
