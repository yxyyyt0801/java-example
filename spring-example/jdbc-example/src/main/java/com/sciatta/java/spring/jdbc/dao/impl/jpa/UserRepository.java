package com.sciatta.java.spring.jdbc.dao.impl.jpa;

import com.sciatta.java.spring.jdbc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yangxiaoyu on 2026/9/22<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
