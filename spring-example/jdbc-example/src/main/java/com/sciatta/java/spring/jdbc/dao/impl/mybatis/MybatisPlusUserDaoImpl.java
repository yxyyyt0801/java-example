package com.sciatta.java.spring.jdbc.dao.impl.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sciatta.java.spring.jdbc.dao.UserDao;
import com.sciatta.java.spring.jdbc.dao.annotation.MybatisPlusUserDao;
import com.sciatta.java.spring.jdbc.entity.PageResult;
import com.sciatta.java.spring.jdbc.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yangxiaoyu on 2026/9/19<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * MybatisPlusUserDaoImpl
 */
@Repository
@AllArgsConstructor
@MybatisPlusUserDao
public class MybatisPlusUserDaoImpl implements UserDao {

    private final UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchCreate(List<User> users) {
        users.forEach(this::create);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<User> page(int pageNum, int pageSize) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(User::getId);

        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return new PageResult<>(userPage.getRecords(), userPage.getTotal(), userPage.getCurrent(), userPage.getSize());
    }
}
