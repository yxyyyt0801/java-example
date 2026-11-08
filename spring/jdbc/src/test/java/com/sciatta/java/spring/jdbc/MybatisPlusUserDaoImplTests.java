package com.sciatta.java.spring.jdbc;

import com.sciatta.java.spring.jdbc.config.AppConfig;
import com.sciatta.java.spring.jdbc.dao.UserDao;
import com.sciatta.java.spring.jdbc.dao.annotation.MybatisPlusUserDao;
import com.sciatta.java.spring.jdbc.entity.PageResult;
import com.sciatta.java.spring.jdbc.entity.User;
import jakarta.annotation.PostConstruct;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        User user = userDao.create(User.createUser());
        assertNotNull(user);

        List<User> all = userDao.findAll();
        assertEquals(3, all.size());
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

    @Test
    public void testBatchCreate() {
        userDao.batchCreate(User.createUsers(20));

        List<User> all = userDao.findAll();
        assertEquals(22, all.size());
    }

    @Test
    public void testPage() {
        userDao.batchCreate(User.createUsers(20));

        PageResult<User> page = userDao.page(1, 10);
        System.out.println(page);
        assertEquals(10, page.getList().size());

        page = userDao.page(2, 10);
        System.out.println(page);
        assertEquals(10, page.getList().size());

        page = userDao.page(3, 10);
        System.out.println(page);
        assertEquals(2, page.getList().size());
    }
}
