package com.woniu.yoga.venue.service;

import com.woniu.yoga.venue.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.venue.vo.CoachInformationVO;
import com.woniu.yoga.venue.vo.VenueInformationVO;

import java.math.BigDecimal;
import java.util.List;


public interface VenueService {
    //根据教练类型寻找教练
    List<Coach> findCoachByVagueConditions (Coach coach, BigDecimal upExpectedSalary,BigDecimal downExpectedSalary);

    //根据venue场馆id查询场馆详情
    Venue findVenueByVenueId(Integer venueId);

    //场馆点击申请签约按钮,生成“等待用户同意”的数据
    int waitCoachForSign(Integer venueId,Integer coachId);

/*    //教练确认签约，改变签约状态为“1”
    int coachSignService(Integer cv_id);

    //教练拒绝签约，改变签约状态为“2”
    int coachRefuseService(int cv_id);*/

    //场馆发布招聘信息
    int venueAddRecruitService(Recruitment recruitment);

    //场馆查询与其签约的教练vo类
    List<CoachInformationVO> venueFindCoach(CoachInformationVO coachInformationVO);

    //场馆解约教练
    int venueBreakCoachService(int coachId);

    //根据场馆id，在场馆表中，添加照片、详情
    int venuePerfectInformationService(Venue venue);

    //根据场馆id，在user表中添加场馆user的信息
    int addVenueUserInformationService(Integer userId, User user);

    //根据场馆id，查询与场馆签约的教练
    List<Coach> findCoachByVenueIdService(Integer venueId);

    //教练添加课程
    int coachAddCourseService(Course course);

    //查询下拉框使用的教练流派
    List<Coach> coachStyle();

    //教练类型
    List<Coach> coachType();

    int getVenueIdByUserId(Integer userId);

    //根据venueId查询userId
    int selectUserIdByVenueId(Integer venueId);

    //根据场馆id查询场馆VO类信息
    VenueInformationVO selectVenueVOByVenueId(Integer venueId);
}
