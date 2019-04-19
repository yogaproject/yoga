package com.woniu.yoga.venue.service;

import com.woniu.yoga.venue.pojo.Coach;
import org.springframework.stereotype.Service;

@Service
public interface CoachService {
    //根据教练id，查询教练详细信息
    Coach findCoachByCoachId(Integer coachId);












}
