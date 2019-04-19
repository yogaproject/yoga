package com.woniu.yoga.venue.dao;

import com.woniu.yoga.venue.pojo.Coach;

import java.util.List;

public interface CoachMapper {
    int deleteByPrimaryKey(Integer coachId);

    int insert(Coach record);

    int insertSelective(Coach record);

    Coach selectByPrimaryKey(Integer coachId);

    int updateByPrimaryKeySelective(Coach record);

    int updateByPrimaryKey(Coach record);


}