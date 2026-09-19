package com.sciatta.java.spring.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yangxiaoyu on 2026/9/19<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * User
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("users")
public class User {
    @TableId(type = IdType.AUTO)    // id 自增
    private Long id;
    private String name;
    private String email;
}
