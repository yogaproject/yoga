package com.woniu.yoga.venue.service.impl;

import com.woniu.yoga.venue.dao.CoachMapper;
import com.woniu.yoga.venue.pojo.Coach;
import com.woniu.yoga.venue.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;

public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachMapper coachMapper;
    @Override
    public Coach findCoachByCoachId(Integer coachId) {
        Coach coach  = coachMapper.selectByPrimaryKey(coachId);
        return coach;
    }
}
