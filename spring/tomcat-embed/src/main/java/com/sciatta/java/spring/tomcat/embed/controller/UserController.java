package com.sciatta.java.spring.tomcat.embed.controller;

import com.sciatta.java.spring.tomcat.embed.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yangxiaoyu on 2026/9/20<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * UserController
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public UserController() {
        // 初始化测试数据
        Long id1 = counter.getAndIncrement();
        users.put(id1, new User(id1, "a", "a@qq.com"));

        Long id2 = counter.getAndIncrement();
        users.put(id2, new User(id2, "b", "b@qq.com"));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<>(users.values()));
    }

    @PostMapping
    @ResponseBody
    public User createUser(@RequestBody User user) {
        Long id = counter.getAndIncrement();
        user.setId(id);

        users.put(id, user);

        return user;
    }
}
