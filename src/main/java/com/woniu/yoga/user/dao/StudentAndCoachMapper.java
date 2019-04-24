package com.woniu.yoga.user.dao;


import com.woniu.yoga.user.pojo.StudentAndCoach;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAndCoachMapper {
    int deleteByPrimaryKey(Integer scId);

    int insert(StudentAndCoach record);

    int insertSelective(StudentAndCoach record);

    StudentAndCoach selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(StudentAndCoach record);

    int updateByPrimaryKey(StudentAndCoach record);
}