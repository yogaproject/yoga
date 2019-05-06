package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.commom.utils.ExceptionUtil;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.user.dao.CourseMapper;
import com.woniu.yoga.user.service.CourseService;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author liufeng
 * @ClassName CourseServiceImpl
 * @Date 2019/4/26 14:27
 * @Version 1.0
 * @Description 课程信息交互
 **/
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Result listCourseByUserId(Integer userId) {
        try {
            List<CourseVO> course =  courseMapper.listCourseByUserId(userId);
            return ResultUtil.actionSuccess("查询成功", course);
        } catch (SQLException e) {
            e.printStackTrace();
            throw ExceptionUtil.getDatabaseException();
        }
    }
}
