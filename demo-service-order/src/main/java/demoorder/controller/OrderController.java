package demoorder.controller;


import com.lengao.springcloud.dto.OrderDTO;
import com.lengao.springcloud.pojo.Order;
import demoorder.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@RestController
@Api(tags = "订单相关")
@Slf4j
public class OrderController {

    @Autowired
    OrderService goodsService;

    @PostMapping("addOrder")
    @ApiOperation("新增订单")
    public int addOrder(@RequestBody OrderDTO order) {
        return goodsService.addOrder(order);
    }

    @DeleteMapping("deleteOrder/{orderId}")
    @ApiOperation("删除订单")
    public int deleteOrder(@PathVariable Integer orderId) {
        return goodsService.deleteOrder(orderId);
    }

    @GetMapping("getOrder/{orderId}")
    @ApiOperation("获取订单")
    public Order getOrder(@PathVariable Integer orderId) {
        return goodsService.getOrder(orderId);
    }

    @PostMapping
    @ApiOperation("修改订单")
    public int updateOrder(Order order) {
        return goodsService.updateOrder(order);
    }

    @GetMapping("feignTest/{msg}")
    public String feignTest(@PathVariable String msg) {
        log.info("OrderController.feignTest");
        return msg;
    }

}
