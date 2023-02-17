package com.lengao.springcloud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2023/2/14
 */
@TableName("t_user")

public class User {

    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    Boolean sex;
}
