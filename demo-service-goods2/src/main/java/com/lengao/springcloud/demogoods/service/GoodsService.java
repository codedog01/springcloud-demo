package com.lengao.springcloud.demogoods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lengao.springcloud.pojo.Goods;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
public interface GoodsService extends IService<Goods> {
    int addGoods(Goods goods);

    int deleteGoods(Integer orderId);

    Goods getGoods(Integer orderId);
}
