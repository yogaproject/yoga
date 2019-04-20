package com.woniu.yoga.user.controller;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.user.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private CoachService coachService;

    //根据教练id查询教练的详细信息（包含了签约教练和没有签约教练，如果场馆id字段不为空，就是签约教练就把教练
    // 和场馆的签约信息也展示出来，否则，不展示此信息）
    @RequestMapping("/coachInformation")
    @ResponseBody
    public Coach selectCoachByCoachId(Integer coachId){
        Coach coach = coachService.findCoachByCoachId(coachId);
        return coach;
    }

    //-------------------------------------招聘------------------------------------------
    //模糊查询场馆list(是否需要招聘(需要场馆已经发布了招聘信息，从招聘表中去查)，招聘流派，工资待遇)
    public List<Venue> selectVenueByConditions(Recruitment recruitment){

        return  null;
    }




}
