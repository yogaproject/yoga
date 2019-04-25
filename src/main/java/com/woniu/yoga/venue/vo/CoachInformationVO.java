package com.woniu.yoga.venue.vo;

public class CoachInformationVO {


    private int coachId;
    private String realName;
    private String coachType;
    private String coachStyle;
    private String userPhone;
    private String userQq;
    private int venueId;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }

    public String getCoachStyle() {
        return coachStyle;
    }

    public void setCoachStyle(String coachStyle) {
        this.coachStyle = coachStyle;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }
    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coacId) {
        this.coachId = coacId;
    }
    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
}
