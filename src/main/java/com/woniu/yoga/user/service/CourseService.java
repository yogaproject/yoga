package com.woniu.yoga.user.service;

import com.woniu.yoga.user.vo.CourseVO;
import com.woniu.yoga.user.vo.Result;

/**
 * @Author liufeng
 * @ClassName CourseService
 * @Date 2019/4/26 14:21
 * @Version 1.0
 * @Description 
 **/
public interface CourseService {


    Result findCourseByCourseId(Integer courseId);
}
