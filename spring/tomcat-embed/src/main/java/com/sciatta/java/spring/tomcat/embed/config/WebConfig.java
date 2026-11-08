package com.sciatta.java.spring.tomcat.embed.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by yangxiaoyu on 2026/9/20<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * WebConfig
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sciatta.java.spring.tomcat.embed")
public class WebConfig implements WebMvcConfigurer {
}
