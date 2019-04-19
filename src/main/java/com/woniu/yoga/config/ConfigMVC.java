package com.woniu.yoga.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class ConfigMVC implements WebMvcConfigurer {


    @Bean
    public DataSourceTransactionManager manager(DataSource dataSource){
        DataSourceTransactionManager manager= new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

}
