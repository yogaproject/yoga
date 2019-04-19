package com.woniu.yoga.venue.service;

import com.woniu.yoga.venue.pojo.Coach;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface VenueService {
    //根据教练类型寻找教练
    List<Coach> findCoachByVagueConditions (Coach coach, BigDecimal up_expected_salry,BigDecimal down_expected_salary);



}
