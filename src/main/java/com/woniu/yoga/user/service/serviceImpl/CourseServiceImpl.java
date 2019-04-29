package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.user.dao.CourseMapper;
import com.woniu.yoga.user.service.CourseService;
import com.woniu.yoga.user.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author liufeng
 * @ClassName CourseServiceImpl
 * @Date 2019/4/26 14:27
 * @Version 1.0
 * @Description 课程信息交互
 **/
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CourseVO findCourseByCourseId(Integer courseId) {
        return courseMapper.findCourseByCourseId(courseId);
    }
}
