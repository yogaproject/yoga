package com.woniu.yoga.venue.dao;

import com.woniu.yoga.venue.pojo.Coach;
import com.woniu.yoga.venue.pojo.Venue;

import java.math.BigDecimal;
import java.util.List;

public interface VenueMapper {
    int deleteByPrimaryKey(Integer venueId);

    int insert(Venue record);

    int insertSelective(Venue record);

    Venue selectByPrimaryKey(Integer venueId);

    int updateByPrimaryKeySelective(Venue record);

    int updateByPrimaryKey(Venue record);

    List<Coach> queryCoachByVagueConditions(Coach coach, BigDecimal up_expected_salary,BigDecimal down_expected_salary);
}