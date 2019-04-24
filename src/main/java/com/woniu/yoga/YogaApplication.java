package com.woniu.yoga;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value = {"com.woniu.yoga.*.dao"})
@EnableTransactionManagement  //启动事物管理
@ServletComponentScan
@EnableSwagger2
@EnableCaching
public class YogaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YogaApplication.class, args);
    }

}
