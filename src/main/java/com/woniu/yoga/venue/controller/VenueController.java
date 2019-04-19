package com.woniu.yoga.venue.controller;

import com.woniu.yoga.venue.pojo.Coach;
import com.woniu.yoga.venue.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;

    //场馆根据选择（教练类型、期望薪资、流派）的id，查询符合条件的教练集合
    @RequestMapping("/coachType")
    public List<Coach> selectCoachByCoachType(Coach coach, BigDecimal up_expected_salary,BigDecimal down_expected_salry){
        List<Coach> listcoach = new ArrayList<>();
        listcoach = venueService.findCoachByVagueConditions(coach,up_expected_salary,down_expected_salry);
        return listcoach;
    }










}
