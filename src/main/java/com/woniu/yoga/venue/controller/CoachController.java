package com.woniu.yoga.venue.controller;

import com.woniu.yoga.venue.pojo.Coach;
import com.woniu.yoga.venue.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private CoachService coachService;

    //根据教练id查询教练的详细信息（包含了签约教练和没有签约教练，如果场馆id字段不为空，就是签约教练就把教练
    // 和场馆的签约信息也展示出来，否则，不展示此信息）
    public Coach selectCoachByCoachId(Integer coachId){
        Coach coach = coachService.findCoachByCoachId(coachId);
        return coach;
    }

    //根据教练id，在课程表里查询课程id的集合





}
