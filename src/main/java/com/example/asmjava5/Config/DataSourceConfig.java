package com.example.asmjava5.Config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        dataSourceBuilder.url("jdbc:sqlserver://localhost;databaseName=QuanLyBanHang");
//        dataSourceBuilder.username("sa");
//        dataSourceBuilder.password("123");
//        return dataSourceBuilder.build();
//    }
}
