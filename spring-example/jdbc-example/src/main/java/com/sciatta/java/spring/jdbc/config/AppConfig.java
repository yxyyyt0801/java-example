package com.sciatta.java.spring.jdbc.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by yangxiaoyu on 2026/9/18<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * AppConfig
 */
@Configuration  // 配置类
@ComponentScan(basePackages = "com.sciatta.java.spring.jdbc")   // 扫描包下组件
@EnableTransactionManagement    // 开启事务管理
@MapperScan("com.sciatta.java.spring.jdbc.dao.impl.mybatis")    // 扫描 MyBatis Mapper 接口
//@EnableJpaRepositories("com.sciatta.java.spring.jdbc.dao.impl.jpa") // 扫码 JPA 接口
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        config.setDriverClassName("org.h2.Driver");
        config.setUsername("sa");
        config.setPassword("");
        config.setAutoCommit(false);
        config.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
        config.setMaximumPoolSize(5);

        HikariDataSource ds = new HikariDataSource(config);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));
        populator.addScript(new ClassPathResource("data.sql"));
        DatabasePopulatorUtils.execute(populator, ds);

        return ds;
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
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, MybatisPlusInterceptor mybatisPlusInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPlugins(mybatisPlusInterceptor); // 挂载拦截器
        return factoryBean.getObject();
    }

    /**
     * 配置 MyBatis-Plus 的分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2)); // 分页拦截器
        return interceptor;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
