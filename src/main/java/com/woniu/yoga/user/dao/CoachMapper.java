package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CoachMapper {
    int deleteByPrimaryKey(Integer coachId);

    int insert(Coach record);

    int insertSelective(Coach record);

    Coach selectByPrimaryKey(Integer coachId);

    int updateByPrimaryKeySelective(Coach record);

    int updateByPrimaryKey(Coach record);

    //教练在招聘表中查询，查询符合条件的场馆招聘信息list
    List<Venue> queryVenueByConditions(Recruitment recruitment);
    /*
     * @Author liufeng
     * @Description //根据瑜伽师id查询专属课程
     **/
    @Select("select course_id courseId,course_name courseName from course where coach_id = #{coachId}")
    List<Course> findCourseByCoachId(Integer coachId);

    /*
     * @Author liufeng
     * @Description //根据瑜伽师id查询场馆名
     **/
    @Select("select real_name from user,coach,venue where coach.coach_id = #{coachId} and coach.venue_id  = venue.venue_id and user.user_id=venue.user_id ")
    String getVenueByCoachId(Integer coachId);
}