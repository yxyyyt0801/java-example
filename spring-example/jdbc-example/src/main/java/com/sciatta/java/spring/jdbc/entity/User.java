package com.sciatta.java.spring.jdbc.entity;

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
public class User {
    private Long id;
    private String name;
    private String email;
}
