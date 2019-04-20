package com.woniu.yoga.venue.dao;

import com.woniu.yoga.venue.pojo.Recruitment;

public interface RecruitmentMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(Recruitment record);

    int insertSelective(Recruitment record);

    Recruitment selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(Recruitment record);

    int updateByPrimaryKey(Recruitment record);
}