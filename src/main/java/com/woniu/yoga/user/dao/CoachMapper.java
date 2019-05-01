package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.vo.CoachStyleVO;
import com.woniu.yoga.user.vo.StudentVO;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.sql.SQLException;
import java.text.CollationElementIterator;
import java.util.List;

@Repository
public interface CoachMapper {
    int deleteByPrimaryKey(Integer coachId)throws SQLException;

    int insert(Coach record) throws SQLException;

    int insertSelective(Coach record)throws SQLException;

    Coach selectByPrimaryKey(Integer coachId)throws SQLException;

    int updateByPrimaryKeySelective(Coach record)throws SQLException;

    int updateByPrimaryKey(Coach record)throws SQLException;

    //教练在招聘表中查询，查询符合条件的场馆招聘信息list
    List<Venue> queryVenueByConditions(Recruitment recruitment)throws SQLException;

    /*
     * @Author liufeng
     * @Description //根据瑜伽师id查询专属课程
     **/
    @Select("select course_id courseId,course_name courseName from course where coach_id = #{coachId} and course_flag = 0")
    List<Course> findCourseByCoachId(Integer coachId)throws SQLException;

    /*
     * @Author liufeng
     * @Description //根据瑜伽师id查询场馆名
     **/
    @Select("select real_name from user,coach,venue where coach.coach_id = #{coachId} and coach.venue_id  = venue.venue_id and user.user_id=venue.user_id and user_flag = 0")
    String getVenueByCoachId(Integer coachId)throws SQLException;
    /*
     * @Author liufeng
     * @Description //查询所有的瑜伽流派
     **/
    @Select("select dict_id,dict_item_name from base_dir where dict_type_code = 1")
    List<CoachStyleVO> listCoachStyles()throws SQLException;

    /*
     * @Author liufeng
     * @Description //根据瑜伽师ID，查询所有的学生
     **/
    @Select("select user_nickname,user_headimg from user,student where user.user_id = student.user_id and student_id in (select student_id from student_coach where coach_id =(select coach_id from coach where user_id = #{userId})) and user_flag = 0")
    @Results(value = {
            @Result(column="user_nickname",property = "nickName"),
            @Result(column = "user_headimg",property = "headImg")
    })
    List<StudentVO> findStudentByUserId(Integer userId)throws SQLException;
    /*
     * @Author liufeng
     * @Description //根据用户id，查询瑜伽师的ID
     **/
    @Select("select coach_id from coach where user_id = #{userId}")
    Integer selectCoachIdByUserId(Integer userId)throws SQLException;
    /*
     * @Author liufeng
     * @Description //根据瑜伽师的id，查找场馆id（如果瑜伽师没有签约场馆，场馆id为空）
     **/
    @Select("select venue_id from user,coach where user.user_id = coach.user_id and user.user_id = #{userId}")
    Integer findVenueBycoachId(Integer userId)throws SQLException;
    /*
     * @Author liufeng
     * @Description //根据场馆id，查询场馆的用户id
     **/
    @Select("select user_id from user,venue where venue.user_id = user.user_id and venue.user_id =#{venueId} and user_flag = 0")
    Integer findVenueByVenueId(Integer venueId)throws SQLException;
    /*
     * @Author liufeng
     * @Description //根据课程id，查询对应的瑜伽师
     **/
    Coach selectByCourseId(Integer entityId)throws SQLException;
    /*
     * @Author liufeng
     * @Description //根据瑜伽师的userId，查询coachId
     **/
    @Select("select coach_id from coach,user where user.user_id = coach.user_id and coach.user_id = #{userId} and user_flag = 0")
    int findCoachIdByUserId(int userId)throws SQLException;
    /*
     * @Author liufeng
     * @Description //在瑜伽师——场馆插入申请浅语的记录
     **/
    @Insert("insert into venue_coach values(default,#{coachId},#{venueId},0)")
    int applyForSign(Integer coachId, Integer venueId)throws SQLException;
    /*
     * @Author liufeng
     * @Description //查看瑜伽师是否已签约场馆
     **/
    @Select("select authentication from coach where coach_id = #{coachId} and coach_flag = 0")
    Integer selectAuthenticationByCoachId(Integer coachId)throws SQLException;
    @Update("update coach_venue set cv_status = #{status} where coachId=#{coachId} and coach_flag = 0")
    int updateCoachVenueStatus(Integer coachId, Integer status)throws SQLException;
    @Select("select cv_id from coach_venue where coach_id =#{coacId} and venue_id =#{venueId} and cv_status = 0")
    Integer selectSignWithCoachIdAndVenueId(Integer coachId, Integer venueId)throws SQLException;
    @Update("update coach set authentication = #{authentication} where coachId  = #{coachId} and coach_flag = 0")
    int updateAuthenticationForSign(Integer coachId, Integer authentication)throws SQLException;
}