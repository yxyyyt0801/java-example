package com.sciatta.java.spring.jdbc.dao.impl.jdbc;

import com.sciatta.java.spring.jdbc.dao.UserDao;
import com.sciatta.java.spring.jdbc.dao.annotation.JdbcTemplateUserDao;
import com.sciatta.java.spring.jdbc.entity.PageResult;
import com.sciatta.java.spring.jdbc.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

/**
 * Created by yangxiaoyu on 2026/9/19<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * JdbcTemplateUserDaoImpl
 */
@Repository
@AllArgsConstructor
@JdbcTemplateUserDao
public class JdbcTemplateUserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        return user;
    };

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        String sql = "INSERT INTO users(name, email) VALUES(?, ?)";

        // 获取key
        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            return ps;
        }, keyHolder);

        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchCreate(List<User> users) {
        for (User user : users) {
            create(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return this.jdbcTemplate.update(sql, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        return this.jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return this.jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return this.jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<User> page(int pageNum, int pageSize) {
        String sql = "SELECT count(*) FROM users";
        Long total = jdbcTemplate.queryForObject(sql, Long.class);

        int offset = (pageNum - 1) * pageSize;
        sql = "SELECT * FROM users ORDER BY id LIMIT ? OFFSET ?";
        List<User> list = jdbcTemplate.query(sql, userRowMapper, pageSize, offset);

        return new PageResult<>(list, total == null ? 0 : total, pageNum, pageSize);
    }
}
