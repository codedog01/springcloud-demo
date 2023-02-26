package com.lengao.springcloud.demogoods.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.lengao.springcloud.demogoods.feign.OrderServiceFeign;
import com.lengao.springcloud.demogoods.service.GoodsService;
import com.lengao.springcloud.dto.OrderDTO;
import com.lengao.springcloud.pojo.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@RestController
@Api("商品相关")
@RefreshScope
public class GoodsController {

    @Value(value = "${cust.config.value}")
    String value;

    @Value(value = "${cust.config.value2}")
    String value2;

    @NacosInjected
    private ConfigService configService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderServiceFeign orderServiceFeign;

    @PostMapping("addGoods")
    @ApiOperation("新增商品")
    int addGoods(@RequestBody Goods goods) {
       return goodsService.addGoods(goods);
    }

    @DeleteMapping("deleteGoods/{orderId}")
    @ApiOperation("删除商品")
    int deleteGoods(@PathVariable Integer orderId) {
       return goodsService.deleteGoods(orderId);
    }

    @GetMapping("getGoods/{orderId}")
    @ApiOperation("获取商品")
    Goods getGoods(@PathVariable Integer orderId) {
        return goodsService.getGoods(orderId);
    }

    @PostMapping("buyGoods")
    @ApiOperation("购买商品")
    int buyGoods(@RequestBody OrderDTO orderDTO) {
        return orderServiceFeign.addOrder(orderDTO);
    }


    @GetMapping("test")
    @ApiOperation("测试")
    String test() {
        return this.value+"\n"+value2;
    }

}
