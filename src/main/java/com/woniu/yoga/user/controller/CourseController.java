package com.woniu.yoga.user.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.CourseService;
import com.woniu.yoga.user.vo.CourseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author liufeng
 * @ClassName CourseController
 * @Date 2019/4/26 14:19
 * @Version 1.0
 * @Description 处理课程专属请求
 **/
@Controller
@RequestMapping("course")
@Api
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
    @PostMapping("listCourseByCoachId")
    @ResponseBody
    @ApiOperation(value = "根据瑜伽师的用户ID查询课程信息")
    @ApiImplicitParam(name = "courseId", value = "课程ID")
    public Result listCoursesByCoachId(HttpSession session, @RequestBody Integer userId) {
        if (userId == null) {
            User user = (User) session.getAttribute("user");
            Integer myId = user.getUserId();
            if (myId == null) {
                myId = 1;
            }
            userId = myId;
        }
        return courseService.listCourseByUserId(userId);
    }
}
