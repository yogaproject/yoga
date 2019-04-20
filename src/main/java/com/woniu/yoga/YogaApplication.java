package com.woniu.yoga;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
<<<<<<< HEAD
@MapperScan(value = {"com.woniu.yoga.cf.dao","com.woniu.yoga.commom.dao",
        "com.woniu.yoga.manage.dao","com.woniu.yoga.venue.dao"})
=======
@MapperScan(value = {"com.woniu.yoga"})
>>>>>>> dev
@EnableTransactionManagement  //启动事物管理
@ServletComponentScan
public class YogaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YogaApplication.class, args);
    }

}
