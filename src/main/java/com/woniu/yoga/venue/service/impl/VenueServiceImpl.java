package com.woniu.yoga.venue.service.impl;

import com.woniu.yoga.venue.dao.VenueMapper;
import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.venue.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    private VenueMapper venueMapper;

    @Override
    public List<Coach> findCoachByVagueConditions(Coach coach, BigDecimal up_expected_salary, BigDecimal down_expected_salary) {
        List<Coach> listcoach = new ArrayList<>();
        venueMapper.queryCoachByVagueConditions(coach,up_expected_salary,down_expected_salary);
        return listcoach;
    }

    @Override
    public Venue findVenueByVenueId(Integer venueId) {
        return venueMapper.selectByPrimaryKey(venueId);
    }

    @Override
    public int waitCoahForSign(Integer venueId, Integer coachId) {
        return venueMapper.waitForSign(venueId,coachId);
    }

    @Override
    public int coachSignService(int cv_id) {
        return venueMapper.coachSignMapper(cv_id);
    }

    @Override
    public int coachRefuseService(int cv_id) {
        return venueMapper.coachRefuseMapper(cv_id);
    }

    @Override
    public int venueAddRecruitService(Recruitment recruitment) {
        return venueMapper.venueAddRecruitMapper(recruitment);
    }

}
