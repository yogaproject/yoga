package com.woniu.yoga.user.dao;


import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.vo.CourseVO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(Integer courseId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer courseId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    @Select("select course_id，user.user_id,real_name,course_name,course_detail,course_img from course,coach,user where user.user_id = coach.user_id and coach.coach_id = course.coach-id course_id  =#{courseId}")
    @Results(value = {
            @Result(column="course_id",property = "courseId"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "real_name",property = "coachName"),
            @Result(column = "course_name",property = "courseName"),
            @Result(column = "course_detail",property = "detail"),
            @Result(column = "course_img",property = "img"),
    })
    CourseVO findCourseByCourseId(Integer courseId);
}