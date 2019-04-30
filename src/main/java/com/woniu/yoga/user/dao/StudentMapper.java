package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Student;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentId)throws SQLException;

    int insert(Student record)throws SQLException;

    int insertSelective(Student record)throws SQLException;

    Student selectByPrimaryKey(Integer studentId)throws SQLException;

    int updateByPrimaryKeySelective(Student record)throws SQLException;

    int updateByPrimaryKey(Student record)throws SQLException;
}