package com.woniu.yoga.venue.service.impl;

import com.woniu.yoga.venue.dao.CoachMapper;
import com.woniu.yoga.venue.dao.VenueMapper;
import com.woniu.yoga.venue.pojo.Coach;
import com.woniu.yoga.venue.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VenueServiceImpl implements VenueService {
    @Autowired
    private VenueMapper venueMapper;

    @Override
    public List<Coach> findCoachByVagueConditions(Coach coach, BigDecimal up_expected_salary, BigDecimal down_expected_salary) {
        List<Coach> listcoach = new ArrayList<>();
        venueMapper.queryCoachByVagueConditions(coach,up_expected_salary,down_expected_salary);
        return listcoach;
    }

    //实现根据教练类型，模糊查询出教练集合

}
