package com.lengao.springcloud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@Data
@TableName("t_goods")
public class Goods extends Model<Goods> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer num;

    private Double price;

}
