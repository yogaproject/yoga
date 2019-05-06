package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.vo.StudentVO;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
    @Select("select user_nickname,user_headimg from user,student where user.user_id = student.user_id and student_id in (select student_id from student_coach where coach_id =(select coach_id from coach where user_id = #{userId}))")
    @Results(value = {
            @Result(column="user_nickname",property = "nickName"),
            @Result(column = "user_headimg",property = "headImg")
    })
    List<StudentVO> findStudentByUserId(Integer userId);
    /*
     * @Author liufeng
     * @Description //根据用户id，查询瑜伽师的ID
     **/
    @Select("select coach_id from coach where user_id = #{userId}")
    Integer selectCoachIdByUserId(Integer userId);
    /*
     * @Author liufeng
     * @Description //根据瑜伽师的id，查找场馆id（如果瑜伽师没有签约场馆，场馆id为空）
     **/
    @Select("select venue_id from user,coach where user.user_id = coach.user_id and user.user_id = #{userId}")
    Integer findVenueBycoachId(Integer userId);
    /*
     * @Author liufeng
     * @Description //根据场馆id，查询场馆的用户id
     **/
    @Select("select user_id from user,venue where venue.user_id = user.user_id and venue.user_id =#{venueId}")
    Integer findVenueByVenueId(Integer venueId);
    /*
     * @Author liufeng
     * @Description //根据课程id，查询对应的瑜伽师
     **/
    Coach selectByCourseId(Integer entityId);
    /*
     * @Author liufeng
     * @Description //根据瑜伽师的userId，查询coachId
     **/
    @Select("select coach_id from coach,user where user.user_id = coach.user_id and coach.user_id = #{userId}")
    int findCoachIdByUserId(int userId);


}