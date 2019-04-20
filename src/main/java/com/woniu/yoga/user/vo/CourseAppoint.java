package com.woniu.yoga.user.vo;

/**
 * @ClassName CourseAppoint
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/16 16:24
 * @Version 1.0
 **/
public enum CourseAppoint {
    ONLINE("平台签约","官方推荐的签约方式"),
    OFFLINE("线下","私下和瑜伽师约定，官方不承担任何责任");
    private String name;
    private String description;
    private CourseAppoint(String name, String description){
        this.name = name;
        this.description = description;
    }

}
