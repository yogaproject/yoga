package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.vo.StudentVO;
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
    /*
     * @Author liufeng
     * @Description //查询所有的瑜伽流派
     **/
    @Select("select dict_item_name from base_dir where dict_type_code = 1")
    List listCoachStyles();

    /*
     * @Author liufeng
     * @Description //根据瑜伽师ID，查询所有的学生
     **/
    @Select("select user_nickname from user,student where user.user_id = student.user_id and student_id in (select student_id from student_coach where coach_id =#{coachId})")
    List<StudentVO> findStudentByUserId(Integer coachId);
    /*
     * @Author liufeng
     * @Description //根据用户id，查询瑜伽师的ID
     **/
    @Select("select coach_id from coach where user_id = #{userId}")
    int selectCoachIdByUserId(Integer userId);
}