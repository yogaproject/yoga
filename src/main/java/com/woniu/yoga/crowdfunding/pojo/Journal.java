package com.woniu.yoga.crowdfunding.pojo;

import java.util.Date;

public class Journal {
    private Integer journalId;

    private Integer cfId;

    private String description;

    private Date createDate;

    private Integer journalFlag;

    public Integer getJournalId() {
        return journalId;
    }

    public void setJournalId(Integer journalId) {
        this.journalId = journalId;
    }

    public Integer getCfId() {
        return cfId;
    }

    public void setCfId(Integer cfId) {
        this.cfId = cfId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getJournalFlag() {
        return journalFlag;
    }

    public void setJournalFlag(Integer journalFlag) {
        this.journalFlag = journalFlag;
    }
}