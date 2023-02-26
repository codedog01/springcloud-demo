package com.lengao.springcloud.demogoods.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.lengao.springcloud.demogoods.feign.OrderServiceFeign;
import com.lengao.springcloud.demogoods.service.GoodsService;
import com.lengao.springcloud.dto.OrderDTO;
import com.lengao.springcloud.pojo.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderServiceFeign orderServiceFeign;

//    @NacosInjected
//    private ConfigService configService;

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


    @GetMapping("test/{msg}")
    @ApiOperation("测试")
    String test(@PathVariable String msg) {
//        String serverStatus = configService.getServerStatus();
//        return orderServiceFeign.feignTest(serverStatus + msg);
                return orderServiceFeign.feignTest("serverStatus" + msg);

    }

}
