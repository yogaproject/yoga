package com.woniu.yoga.venue.service.impl;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.venue.dao.VenueMapper;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.venue.service.VenueService;
import com.woniu.yoga.venue.vo.CoachInformationVO;
import com.woniu.yoga.venue.vo.VenueInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class VenueServiceImpl implements VenueService{
    @Autowired
    private VenueMapper venueMapper;

    @Override
    public List<Coach> findCoachByVagueConditions(Coach coach, BigDecimal upExpectedSalary, BigDecimal downExpectedSalary) {
        List<Coach> listcoach = venueMapper.queryCoachByVagueConditions(coach,upExpectedSalary,downExpectedSalary);
        return listcoach;
    }

    @Override
    public Venue findVenueByVenueId(Integer venueId) {
        return venueMapper.selectByPrimaryKey(venueId);
    }

    @Override
    public int waitCoachForSign(Integer venueId, Integer coachId) {
        return venueMapper.waitForSign(venueId,coachId);
    }

/*    @Override
    public int coachSignService(Integer cv_id) {
        return venueMapper.coachSignMapper(cv_id);
    }

    @Override
    public int coachRefuseService(int cv_id) {
        return venueMapper.coachRefuseMapper(cv_id);
    }*/

    @Override
    public int venueAddRecruitService(Recruitment recruitment) {
        return venueMapper.venueAddRecruitMapper(recruitment);
    }

    @Override
    public List<CoachInformationVO> venueFindCoach(CoachInformationVO coachInformationVO) {
        List list = venueMapper.venueQueryCoach(coachInformationVO);
        return list;
    }

    @Override
    public int venueBreakCoachService(int coachId) {

        return venueMapper.venueBreakCoachMapper(coachId);
    }

    @Override
    public int venuePerfectInformationService(Venue venue) {
        return venueMapper.venuePerfectInformationMapper(venue);
    }

    @Override
    public int addVenueUserInformationService(Integer userId, User user) {
        return venueMapper.addVenueUserInformationMapper(userId,user);
    }

    @Override
    public List<Coach> findCoachByVenueIdService(Integer venueId) {
        return venueMapper.queryCoachByVenueId(venueId);
    }

    @Override
    public int coachAddCourseService(Course course) {
        return venueMapper.coachAddCourseMapper(course);
    }

    @Override
    public List<Coach> coachStyle() {
        return venueMapper.coachStyle();
    }

    @Override
    public List<Coach> coachType() {
        return venueMapper.coachType();
    }

    @Override
    public int getVenueIdByUserId(Integer userId) {
        return venueMapper.getVenueIdByUserId(userId);
    }

    @Override
    public int selectUserIdByVenueId(Integer venueId) {
        return venueMapper.selectUserIdByVenueId(venueId);
    }

    @Override
    public VenueInformationVO selectVenueVOByVenueId(Integer venueId) {
        return venueMapper.selectVenueVOByVenueId(venueId);
    }


}
