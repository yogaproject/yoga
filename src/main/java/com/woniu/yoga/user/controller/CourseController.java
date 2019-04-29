package com.woniu.yoga.user.controller;

import com.woniu.yoga.user.service.CourseService;
import com.woniu.yoga.user.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author liufeng
 * @ClassName CourseController
 * @Date 2019/4/26 14:19
 * @Version 1.0
 * @Description 处理课程专属请求
 **/
public class CourseController {
    @Autowired
    private CourseService courseService;
    /*
     * @Author liufeng
     * @Date
     * @Description //根据课程Id查询课程详细信息
     * @Param：课程di（Integer）
     * @return  Result(通用返回类型，包含状态码，提示信息和详细内容)
     **/
    @RequestMapping("findCourseByCourseId/{courseId}")
    CourseVO findCourseByCourseId(@PathVariable(value = "courseId") Integer courseId){
        return courseService.findCourseByCourseId(courseId);
    }
}
