package com.lengao.springcloud.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Data
@TableName("t_order")
public class Order extends Model<Order> {

    @TableId(type = IdType.AUTO)
    Integer id;
    String code;
    Double amount;
    @TableField(fill = FieldFill.INSERT)
    Integer status;
    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;
    @Version
    Integer version;

}
