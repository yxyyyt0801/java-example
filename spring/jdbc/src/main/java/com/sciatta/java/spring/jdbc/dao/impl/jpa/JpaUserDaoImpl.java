package com.sciatta.java.spring.jdbc.dao.impl.jpa;

import com.sciatta.java.spring.jdbc.dao.UserDao;
import com.sciatta.java.spring.jdbc.dao.annotation.JpaUserDao;
import com.sciatta.java.spring.jdbc.entity.PageResult;
import com.sciatta.java.spring.jdbc.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by yangxiaoyu on 2026/9/22<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * JpaUserDaoImpl
 */
@Repository
@AllArgsConstructor
@JpaUserDao
public class JpaUserDaoImpl implements UserDao {

    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void batchCreate(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public int delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            return 1;
        }
        return 0;
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElse(null);
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        Iterable<User> all = userRepository.findAll();
        all.forEach(result::add);
        return result;
    }

    @Override
    public PageResult<User> page(int pageNum, int pageSize) {
        Page<User> result = userRepository.findAll(PageRequest.of(pageNum - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "id")));

        return new PageResult<>(result.getContent(), result.getTotalElements(),
                result.getPageable().getPageNumber(), result.getPageable().getPageSize());
    }
}
