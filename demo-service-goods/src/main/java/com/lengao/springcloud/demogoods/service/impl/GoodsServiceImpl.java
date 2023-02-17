package com.lengao.springcloud.demogoods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lengao.springcloud.demogoods.mapper.GoodsMapper;
import com.lengao.springcloud.demogoods.service.GoodsService;
import com.lengao.springcloud.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public int addGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public int deleteGoods(Integer orderId) {
        return goodsMapper.deleteById(orderId);
    }

    @Override
    public Goods getGoods(Integer orderId) {
        return goodsMapper.selectById(orderId);
    }

}
