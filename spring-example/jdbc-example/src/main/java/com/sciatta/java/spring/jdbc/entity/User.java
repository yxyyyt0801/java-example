package com.sciatta.java.spring.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yangxiaoyu on 2026/9/19<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * User
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("users")
@Entity
@Table(name = "users")
public class User {
    @TableId(type = IdType.AUTO)    // id 自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public static User createUser() {
        String name = UUID.randomUUID().toString().replace("-", "");
        User user = new User();
        user.setName(name);
        user.setEmail(name + "@qq.com");
        return user;
    }

    public static List<User> createUsers(int howMany) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            users.add(createUser());
        }

        return users;
    }
}
