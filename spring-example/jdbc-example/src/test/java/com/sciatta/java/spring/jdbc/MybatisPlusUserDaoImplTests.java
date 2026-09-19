package com.sciatta.java.spring.jdbc;

import com.sciatta.java.spring.jdbc.config.AppConfig;
import com.sciatta.java.spring.jdbc.dao.UserDao;
import com.sciatta.java.spring.jdbc.dao.annotation.MybatisPlusUserDao;
import com.sciatta.java.spring.jdbc.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by yangxiaoyu on 2026/9/19<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * MybatisPlusUserDaoImplTests
 */
@SpringBootTest(classes = AppConfig.class)
@Transactional
public class MybatisPlusUserDaoImplTests {
    @MybatisPlusUserDao
    @Autowired
    private UserDao userDao;

    @Test
    public void testCreate() {
        User user = userDao.create(createUser());
        assertEquals(3, user.getId());
    }

    @Test
    public void testDelete() {
        User user = userDao.create(createUser());

        int delete = userDao.delete(user.getId());

        assertEquals(1, delete);
    }

    @Test
    public void testUpdate() {
        User user = userDao.findById(2L);
        user.setName("m");

        int update = userDao.update(user);
        assertEquals(1, update);

        user = userDao.findById(2L);
        assertEquals("m", user.getName());
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(2L);
        assertEquals("b", user.getName());
    }

    @Test
    public void testFindAll() {
        List<User> all = userDao.findAll();
        assertEquals(2, all.size());

        System.out.println(all);
    }

    private User createUser() {
        User user = new User();
        user.setName("c");
        user.setEmail("c@qq.com");
        return user;
    }

}
