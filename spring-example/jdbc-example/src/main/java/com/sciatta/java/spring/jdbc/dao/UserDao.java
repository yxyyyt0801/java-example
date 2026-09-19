package com.sciatta.java.spring.jdbc.dao;

import com.sciatta.java.spring.jdbc.entity.User;

import java.util.List;

/**
 * Created by yangxiaoyu on 2026/9/19<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * UserDao
 */
public interface UserDao {
    User create(User user);

    int delete(Long id);

    int update(User user);

    User findById(Long id);

    List<User> findAll();
}
