package com.woniu.yoga.user.vo;

/**
 * @Author liufeng
 * @ClassName SearchConditionVO
 * @Date 2019/4/19 9:37
 * @Version 1.0
 * @Description 学员搜索瑜伽师、场馆的条件
 **/

public class SearchConditionVO {
    //姓名
    private String realName;
    //位置：经度
    private Float longitude;
    //位置：纬度
    private Float latitude;
    //距离“我”的距离，单位为米
    private int round;
    //搜索的条件：2为搜索教练，3为搜索场馆，其余不合法
    private int roleId;
    //    //最低课时费，限定整数；场馆无需此项
//    private int lowPrice;
    //教练流派，需要从数据库字典查询，然后选择；场馆无需此项
    private int coachStyle;
    //教练认证方式，值为场馆、平台或不限，其余不合法；场馆无需此项
    private String authenticationMethod;
    //教练空闲时间，值为不限、早、中、晚、全天5选一；场馆无需此项
    private String freeTime;

    public SearchConditionVO() {
        round = 5000;
        roleId = 2;
        coachStyle = 0;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

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

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }
}
