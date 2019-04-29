package com.woniu.yoga.crowdfunding.dao;

import com.woniu.yoga.crowdfunding.pojo.Journal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalMapper {
    int deleteByPrimaryKey(Integer journalId);

    int insert(Journal record);

    int insertSelective(Journal record);

    Journal selectByPrimaryKey(Integer journalId);

    int updateByPrimaryKeySelective(Journal record);

    int updateByPrimaryKey(Journal record);

    List<Journal> getJournals(Integer cfId);
}