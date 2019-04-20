package com.woniu.yoga.user.dao;

import com.woniu.yoga.sign.entity.StudentAndCoach;

public interface StudentAndCoachMapper {
    int deleteByPrimaryKey(Integer scId);

    int insert(StudentAndCoach record);

    int insertSelective(StudentAndCoach record);

    StudentAndCoach selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(StudentAndCoach record);

    int updateByPrimaryKey(StudentAndCoach record);
}