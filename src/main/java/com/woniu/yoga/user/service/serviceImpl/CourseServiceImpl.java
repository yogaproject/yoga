package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.user.dao.CourseMapper;
import com.woniu.yoga.user.service.CourseService;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.CourseVO;
import com.woniu.yoga.user.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

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
    public Result findCourseByCourseId(Integer courseId) throws RuntimeException {
        try {
            CourseVO course = (CourseVO) courseMapper.findCourseByCourseId(courseId);
            return ResultUtil.actionSuccess("查询成功", course);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
