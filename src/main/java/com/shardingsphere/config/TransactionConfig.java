//package com.shardingsphere.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class TransactionConfig {
//    /**
//     * 关联 datasource 到 spring 的 PlatformTransactionManager（没有直接使用 jdbc 原生事务）
//     */
//    @Bean
//    public PlatformTransactionManager txManager(@Qualifier("shardingDataSource") final DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    /**
//     * 通过 jdbcTemplate 简化原生 sharding-jdbc SQL 的使用
//     */
//    @Bean
//    public JdbcTemplate jdbcTemplate(@Qualifier("shardingDataSource") final DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//}