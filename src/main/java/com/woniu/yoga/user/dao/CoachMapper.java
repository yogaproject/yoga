package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;

import java.util.List;

public interface CoachMapper {
    int deleteByPrimaryKey(Integer coachId);

    int insert(Coach record);

    int insertSelective(Coach record);

    Coach selectByPrimaryKey(Integer coachId);

    int updateByPrimaryKeySelective(Coach record);

    int updateByPrimaryKey(Coach record);

    //教练在招聘表中查询，查询符合条件的场馆招聘信息list
    List<Venue> queryVenueByConditions(Recruitment recruitment);



}