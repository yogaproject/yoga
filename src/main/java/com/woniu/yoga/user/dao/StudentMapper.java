package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}