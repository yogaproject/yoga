package com.woniu.yoga.venue.dao;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.venue.vo.CoachInformationVO;
import com.woniu.yoga.venue.vo.VenueInformationVO;
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
    List<Coach> queryCoachByVagueConditions(@Param("coach") Coach coach, @Param("upExpectedSalary") BigDecimal upExpectedSalary,@Param("downExpectedSalary") BigDecimal downExpectedSalary);

    //场馆点击申请签约按钮,生成“等待用户同意”的数据
    int waitForSign(@Param("venueId") Integer venueId,@Param("coachId") Integer coachId);

/*    //教练确认签约，改变签约状态为“1”,venue表增加签约教练数据
    int coachSignMapper(int cv_id);


    //教练拒绝签约，改变签约状态为“2”
    int coachRefuseMapper(int cv_id);*/

    //场馆发布招聘信息
    int venueAddRecruitMapper(@Param("recruitment") Recruitment recruitment);

    //场馆查询与其签约的教练(coachVO )
    List<Coach> venueQueryCoach(@Param("coachInformationVO") CoachInformationVO coachInformationVO);

    //场馆解约教练
    int venueBreakCoachMapper(int coachId);

    //根据场馆id，在场馆表中，添加照片、详情
    int venuePerfectInformationMapper(@Param("venue") Venue venue);

    //根据场馆id，在user表中添加场馆user的信息
    @Insert("update `user` set user_phone = #{user.userPhone},user_location=#{user.userLocation},user_nickname=#{user.userNickname} where user_id = #{userId}")
    int addVenueUserInformationMapper(@Param("userId") Integer userId, @Param("user") User user);

    //根据场馆id，查询与场馆签约的教练信息
    @Select("select * from venue_coach where venue_id = #{venueId}")
    List<Coach> queryCoachByVenueId(@Param("venueId") Integer venueId);

    //场馆给教练安排课程
    int coachAddCourseMapper (Course course);

    //查询下拉框使用的教练流派
    @Select("select dict_item_name coach_style  from base_dict where dict_type_code = 1")
    List<Coach> coachStyle();

    //教练类型
    @Select("select dict_item_name coach_type  from base_dict where dict_type_code = 4")
    List<Coach> coachType();

    //获取场馆id
    @Select("select venue_id from venue where user_id = #{userId}")
    int getVenueIdByUserId(@Param("userId") int userId);

    //根据venueId查询userId
    @Select("select user_id from venue where venue_id = #{venueId}")
    int selectUserIdByVenueId(@Param("venueId") Integer venueId);

    //根据场馆id，查询场馆VO类信息
    VenueInformationVO selectVenueVOByVenueId(@Param("venueId") Integer venueId);
}