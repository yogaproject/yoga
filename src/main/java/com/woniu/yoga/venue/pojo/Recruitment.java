package com.woniu.yoga.venue.pojo;

import java.math.BigDecimal;

public class Recruitment {
    private Integer recId;

    private Integer venueId;

    private Integer coachType;

    private Integer coachStyle;

    private BigDecimal salaryUp;

    private BigDecimal salaryDown;

    private Integer recFlag;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getCoachType() {
        return coachType;
    }

    public void setCoachType(Integer coachType) {
        this.coachType = coachType;
    }

    public Integer getCoachStyle() {
        return coachStyle;
    }

    public void setCoachStyle(Integer coachStyle) {
        this.coachStyle = coachStyle;
    }

    public BigDecimal getSalaryUp() {
        return salaryUp;
    }

    public void setSalaryUp(BigDecimal salaryUp) {
        this.salaryUp = salaryUp;
    }

    public BigDecimal getSalaryDown() {
        return salaryDown;
    }

    public void setSalaryDown(BigDecimal salaryDown) {
        this.salaryDown = salaryDown;
    }

    public Integer getRecFlag() {
        return recFlag;
    }

    public void setRecFlag(Integer recFlag) {
        this.recFlag = recFlag;
    }
}