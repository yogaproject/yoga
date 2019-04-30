package com.woniu.yoga.user.controller;

import com.woniu.yoga.user.service.CourseService;
import com.woniu.yoga.user.vo.CourseVO;
import com.woniu.yoga.user.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("findCourseByCourseId/{courseId}")
    @ResponseBody
    @ApiOperation(value = "根据课程ID查询课程信息")
    @ApiImplicitParam(name = "courseId",value = "课程ID")
    Result findCourseByCourseId(@PathVariable(value = "courseId") Integer courseId){
        return courseService.findCourseByCourseId(courseId);
    }
}
