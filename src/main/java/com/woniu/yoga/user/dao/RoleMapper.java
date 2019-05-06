package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Role;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId)throws SQLException;

    int insert(Role record)throws SQLException;

    int insertSelective(Role record)throws SQLException;

    Role selectByPrimaryKey(Integer roleId)throws SQLException;

    int updateByPrimaryKeySelective(Role record)throws SQLException;

    int updateByPrimaryKey(Role record)throws SQLException;
}