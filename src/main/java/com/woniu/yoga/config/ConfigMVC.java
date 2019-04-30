package com.woniu.yoga.config;


import com.woniu.yoga.crowdfunding.interceptor.ResourceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import javax.sql.DataSource;

@Configuration
public class ConfigMVC implements WebMvcConfigurer {

    @Autowired
    private ResourceInterceptor resourceInterceptor;


    @Bean(name = "transactionManager")
    public DataSourceTransactionManager manager(DataSource dataSource){
        DataSourceTransactionManager manager= new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/charts.html").setViewName("charts");
        registry.addViewController("/tables.html").setViewName("tables");
        // registry.addViewController("/house_list.html").setViewName("houseList");
        // registry.addViewController("/loupanchart.html").setViewName("loupanchart");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(resourceInterceptor);
        registration.addPathPatterns("/**");
        registration.order(1);
    }

    /**
     * @Description 解决因springboot与thymeleaf在合作过程中的bug，该bug会导致static中的资源加载不完全
     * @Author HanFeng
     * @Create in 2019/4/26 11:49
     * @param
     * @return
     * @Version v1.0
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(
                ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
