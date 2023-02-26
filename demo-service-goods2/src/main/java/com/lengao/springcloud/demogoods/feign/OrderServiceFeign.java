package com.lengao.springcloud.demogoods.feign;

import com.lengao.springcloud.dto.OrderDTO;
import com.lengao.springcloud.pojo.Order;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@FeignClient(name = "service-order")
@Component
public interface OrderServiceFeign {

    @PostMapping("updateOrder")
    int updateOrder(@RequestBody Order order);

    @PostMapping("addOrder")
    int addOrder(@RequestBody OrderDTO order);

    @GetMapping("feignTest/{msg}")
    public String feignTest(@PathVariable String msg);

}
