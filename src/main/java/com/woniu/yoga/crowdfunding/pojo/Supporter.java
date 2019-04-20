package com.woniu.yoga.crowdfunding.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Supporter {
    private Integer supId;

    private Integer cfId;

    private Integer userId;

    private BigDecimal supMoney;

    private String supComment;

    private Date supDate;

    private Integer supFlag;

    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    public Integer getCfId() {
        return cfId;
    }

    public void setCfId(Integer cfId) {
        this.cfId = cfId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getSupMoney() {
        return supMoney;
    }

    public void setSupMoney(BigDecimal supMoney) {
        this.supMoney = supMoney;
    }

    public String getSupComment() {
        return supComment;
    }

    public void setSupComment(String supComment) {
        this.supComment = supComment;
    }

    public Date getSupDate() {
        return supDate;
    }

    public void setSupDate(Date supDate) {
        this.supDate = supDate;
    }

    public Integer getSupFlag() {
        return supFlag;
    }

    public void setSupFlag(Integer supFlag) {
        this.supFlag = supFlag;
    }
}