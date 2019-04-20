package com.woniu.yoga.user.dto;

/**
 * @Author liufeng
 * @ClassName SearchConditionDTO
 * @Date 2019/4/19 10:04
 * @Version 1.0
 * @Description TODO
 **/
public class SearchConditionDTO {
    //姓名
    private String realName;
    //位置边界：东方经度
    private double eastLongitude;
    //位置边界：西方经度
    private double westLongitude;
    //位置边界：北方纬度
    private double nothLatitude;
    //位置边界：南方纬度
    private double southLatitude;
    //搜索的条件：2为搜索教练，3为搜索场馆，其余不合法
    private int roleId;
//    //最低课时费，限定整数；场馆无需此项
//    private int lowPrice;
    //教练流派，需要从数据库字典查询，然后选择；场馆无需此项
    private int coachStyle;
    //教练认证方式，值为“不限”、“场馆认证”或者“平台认证”，其余不合法；场馆无需此项
    private String authenticationMethod;
    //教练空闲时间：起始时间——结束时间，格式"hh:dd"；场馆无需此项
    private String freeTimeStart;
    private String freeTimeEnd;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public double getEastLongitude() {
        return eastLongitude;
    }

    public void setEastLongitude(double eastLongitude) {
        this.eastLongitude = eastLongitude;
    }

    public double getWestLongitude() {
        return westLongitude;
    }

    public void setWestLongitude(double westLongitude) {
        this.westLongitude = westLongitude;
    }

    public double getNothLatitude() {
        return nothLatitude;
    }

    public void setNothLatitude(double nothLatitude) {
        this.nothLatitude = nothLatitude;
    }

    public double getSouthLatitude() {
        return southLatitude;
    }

    public void setSouthLatitude(double southLatitude) {
        this.southLatitude = southLatitude;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

//    public int getLowPrice() {
//        return lowPrice;
//    }
//
//    public void setLowPrice(int lowPrice) {
//        this.lowPrice = lowPrice;
//    }

    public int getCoachStyle() {
        return coachStyle;
    }

    public void setCoachStyle(int coachStyle) {
        this.coachStyle = coachStyle;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public String getFreeTimeStart() {
        return freeTimeStart;
    }

    public void setFreeTimeStart(String freeTimeStart) {
        this.freeTimeStart = freeTimeStart;
    }

    public String getFreeTimeEnd() {
        return freeTimeEnd;
    }

    public void setFreeTimeEnd(String freeTimeEnd) {
        this.freeTimeEnd = freeTimeEnd;
    }
}
