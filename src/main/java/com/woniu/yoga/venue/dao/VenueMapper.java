package com.woniu.yoga.venue.dao;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.venue.vo.CoachInformationVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface VenueMapper {
    int deleteByPrimaryKey(Integer venueId);

    int insert(Venue record);

    int insertSelective(Venue record);

    Venue selectByPrimaryKey(Integer venueId);

    int updateByPrimaryKeySelective(Venue record);

    int updateByPrimaryKey(Venue record);

    //场馆模糊查询符合条件的教练
    List<Coach> queryCoachByVagueConditions(Coach coach, BigDecimal up_expected_salary,BigDecimal down_expected_salary);

    //场馆点击申请签约按钮,生成“等待用户同意”的数据
    int waitForSign(Integer venueId,Integer coachId);

    //教练确认签约，改变签约状态为“1”,venue表增加签约教练数据
    int coachSignMapper(int cv_id);


    //教练拒绝签约，改变签约状态为“2”
    int coachRefuseMapper(int cv_id);

    //场馆发布招聘信息
    int venueAddRecruitMapper(Recruitment recruitment);

    //场馆查询与其签约的教练(coachVO )
    List<Coach> venueQueryCoach(CoachInformationVO coachInformationVO);

    //场馆解约教练
    int venueBreakCoachMapper(int coachId);

    //根据场馆id，在场馆表中，添加照片、详情
    int venuePerfectInformationMapper(Venue venue);

    //根据场馆id，在user表中添加场馆user的信息
    @Insert("update `user` set user_phone = #{user.userPhone},user_location=#{user.userLocation},user_nickname=#{user.userNickname} where user_id = #{userId}")
    int addVenueUserInformationMapper(@Param("userId") Integer userId, @Param("user") User user);

    //根据场馆id，查询与场馆签约的教练信息
    @Select("select * from venue_coach where venueId = #{0}")
    List<Coach> queryCoachByVenueId(Integer venueId);

    //场馆给教练安排课程
    int coachAddCourseMapper (Course course);



}