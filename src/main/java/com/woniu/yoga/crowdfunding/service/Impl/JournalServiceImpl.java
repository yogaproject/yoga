package com.woniu.yoga.crowdfunding.service.Impl;

import com.woniu.yoga.crowdfunding.dao.JournalMapper;
import com.woniu.yoga.crowdfunding.pojo.Journal;
import com.woniu.yoga.crowdfunding.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project yoga
 * @Description
 * @Author HanFeng
 * @Create in 2019/4/23 10:12
 */
@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalMapper journalMapper;

    @Override
    public List<Journal> getJournals(Integer cfId) {
        return journalMapper.getJournals(cfId);
    }

    @Override
    public int saveJournal(Journal journal) {
        return journalMapper.insertSelective(journal);
    }
}
