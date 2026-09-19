package com.sciatta.java.spring.jdbc.config;

import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by yangxiaoyu on 2026/9/18<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * AppConfig
 */
// 配置类
@Configuration
// 扫描包下组件
@ComponentScan(basePackages = "com.sciatta.java.spring.jdbc")
// 开启事务管理
@EnableTransactionManagement
@MapperScan("com.sciatta.java.spring.jdbc.dao.impl.mybatis")    // 扫描 MyBatis Mapper 接口
public class AppConfig {
    // 定义数据源
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb")
                // 启动应用时执行脚本
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
    }

    /**
     * Spring JdbcTemplate
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 配置 MyBatis-Plus 的 SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // 实体类别名包路径，方便 XML 中引用
        factoryBean.setTypeAliasesPackage("com.sciatta.java.spring.jdbc.entity");
        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
