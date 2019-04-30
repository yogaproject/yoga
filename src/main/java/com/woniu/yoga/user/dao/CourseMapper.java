package com.woniu.yoga.user.dao;


import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.vo.CourseVO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(Integer courseId)throws SQLException;

    int insert(Course record)throws SQLException;

    int insertSelective(Course record)throws SQLException;

    Course selectByPrimaryKey(Integer courseId)throws SQLException;

    int updateByPrimaryKeySelective(Course record)throws SQLException;

    int updateByPrimaryKey(Course record)throws SQLException;

    @Select("select course_id，user.user_id,real_name,course_name,course_detail,course_img from course,coach,user where user.user_id = coach.user_id and coach.coach_id = course.coach_id course_id  =#{courseId}")
    @Results(value = {
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "real_name", property = "coachName"),
            @Result(column = "course_name", property = "courseName"),
            @Result(column = "course_detail", property = "detail"),
            @Result(column = "course_img", property = "img"),
    })
    CourseVO findCourseByCourseId(Integer courseId)throws SQLException;

    /*
     * @Author liufeng
     * @Description //根据瑜伽师id查找是否存在同名课程
     **/
    @Select("select course_id from course,coach where course.coach_id = coach.coach_id and course_name = #{courseName} and coach.coach_id = #{coachId} and course_flag = 0")
    Integer selectByCoachIdAndCOurseName(Integer coachId, String courseName)throws SQLException;

    @Update("update course set course_flag = 1 where course_id = #{courseId} and course_flag = 0")
    int deleteCourse()throws SQLException;
}