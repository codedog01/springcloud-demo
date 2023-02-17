package com.lengao.springcloud.dto;

import com.lengao.springcloud.pojo.OrderDetail;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Data
public class OrderDTO {
    String code;
    Double amount;
    List<OrderDetail> goodsList;
}
