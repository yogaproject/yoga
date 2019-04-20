package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.user.dao.CoachMapper;
import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachMapper coachMapper;
    @Override
    public Coach findCoachByCoachId(Integer coachId) {
        Coach coach  = coachMapper.selectByPrimaryKey(coachId);
        return coach;
    }
}
