package com.woniu.yoga.venue.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Coach {
    private Integer coachId;

    private Integer userId;

    private Date startTime;

    private Date endTime;

    private String coachType;

    private Integer coachStyle;

    private Integer coachStatus;

    private Integer fullClass;

    private BigDecimal expectedSalary;

    private Integer authentication;

    private Integer dealAccount;

    private Integer notCompleted;

    private Integer goodComment;

    private Integer badComment;

    private Integer commonComment;

    private Integer coachFlag;

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }

    public Integer getCoachStyle() {
        return coachStyle;
    }

    public void setCoachStyle(Integer coachStyle) {
        this.coachStyle = coachStyle;
    }

    public Integer getCoachStatus() {
        return coachStatus;
    }

    public void setCoachStatus(Integer coachStatus) {
        this.coachStatus = coachStatus;
    }

    public Integer getFullClass() {
        return fullClass;
    }

    public void setFullClass(Integer fullClass) {
        this.fullClass = fullClass;
    }

    public BigDecimal getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(BigDecimal expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public Integer getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Integer authentication) {
        this.authentication = authentication;
    }

    public Integer getDealAccount() {
        return dealAccount;
    }

    public void setDealAccount(Integer dealAccount) {
        this.dealAccount = dealAccount;
    }

    public Integer getNotCompleted() {
        return notCompleted;
    }

    public void setNotCompleted(Integer notCompleted) {
        this.notCompleted = notCompleted;
    }

    public Integer getGoodComment() {
        return goodComment;
    }

    public void setGoodComment(Integer goodComment) {
        this.goodComment = goodComment;
    }

    public Integer getBadComment() {
        return badComment;
    }

    public void setBadComment(Integer badComment) {
        this.badComment = badComment;
    }

    public Integer getCommonComment() {
        return commonComment;
    }

    public void setCommonComment(Integer commonComment) {
        this.commonComment = commonComment;
    }

    public Integer getCoachFlag() {
        return coachFlag;
    }

    public void setCoachFlag(Integer coachFlag) {
        this.coachFlag = coachFlag;
    }
}