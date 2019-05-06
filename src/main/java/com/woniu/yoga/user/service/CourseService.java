package com.woniu.yoga.user.service;

import com.woniu.yoga.commom.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author liufeng
 * @ClassName CourseService
 * @Date 2019/4/26 14:21
 * @Version 1.0
 * @Description 
 **/
@Service
@Transactional
public interface CourseService {



    Result listCourseByUserId(Integer usrId) throws RuntimeException;
}
