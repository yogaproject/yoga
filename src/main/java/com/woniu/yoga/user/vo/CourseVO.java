package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName CourseVO
 * @Date 2019/4/26 14:22
 * @Version 1.0
 * @Description 返回课程信息
 **/
@Data
public class CourseVO {
    //课程id
    private int courseId;
    //课程所属瑜伽师的用户id
    private int userId;
    //课程所属瑜伽师真实姓名
    private String coachName;
    //课程名
    private String courseName;
    //课程描述信息
    private String detail;
    //课程图片
    private String img;



}
