package com.woniu.yoga.user.vo;

/**
 * @Author liufeng
 * @ClassName UserVO
 * @Date 2019/4/18 15:57
 * @Version 1.0
 * @Description 保存一个用户的粗略信息，如编号，姓名，地址
 **/
public class UserVO {
    private Integer userId;
    private String realName;
    private Float longitude;
    private Float latitude;
    private String headImg;


    public UserVO() {
    }

    public UserVO(Integer userId, String realName, Float longitude, Float latitude) {
        this.userId = userId;
        this.realName = realName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "{" +
                "userId:" + userId +
                ", realName:'" + realName + '\'' +
                ", longitude:" + longitude +
                ", latitude:" + latitude +
                ", headImg:'" + headImg + '\'' +
                '}';
    }

    public static void main(String[] args) {
        UserVO userVO = new UserVO();
        System.out.println(userVO);
    }
}
