package com.woniu.yoga.cf.dao;

import com.woniu.yoga.cf.pojo.Journal;

public interface JournalMapper {
    int deleteByPrimaryKey(Integer journalId);

    int insert(Journal record);

    int insertSelective(Journal record);

    Journal selectByPrimaryKey(Integer journalId);

    int updateByPrimaryKeySelective(Journal record);

    int updateByPrimaryKey(Journal record);
}