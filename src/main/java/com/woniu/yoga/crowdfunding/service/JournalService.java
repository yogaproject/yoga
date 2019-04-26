package com.woniu.yoga.crowdfunding.service;

import com.woniu.yoga.crowdfunding.pojo.Journal;

import java.util.List;

public interface JournalService {

    /**
     * @Description 获得当前项目的所有日报
     * @Author HanFeng
     * @Create in 2019/4/23 15:04
     * @param cfId
     * @return
     * @Version v1.0
     */
    List<Journal> getJournals(Integer cfId);

    /**
     * @Description 增加一篇日报
     * @Author HanFeng
     * @Create in 2019/4/23 15:05
     * @param journal
     * @return
     * @Version v1.0
     */
    int saveJournal(Journal journal);
}
