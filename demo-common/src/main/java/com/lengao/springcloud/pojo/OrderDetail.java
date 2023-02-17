package com.lengao.springcloud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Data
@TableName("t_order_detail")

public class OrderDetail {
    Integer orderId;
    Integer goodsId;
    Double price;
    Double actualPrice;
    Integer num;
}
