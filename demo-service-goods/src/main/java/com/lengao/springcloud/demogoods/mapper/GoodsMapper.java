package com.lengao.springcloud.demogoods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lengao.springcloud.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Repository
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
