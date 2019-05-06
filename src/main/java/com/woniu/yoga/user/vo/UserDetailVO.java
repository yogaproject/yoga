package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/5/6 11:12
 * @Version 1.0
 */
@Data
public class UserDetailVO {
    private Integer userId;
    private String userPwd;
    private String salt;
    private Integer roleId;
    private String userPhone;
    private String userEmail;
    private String userWechat;
    private String userQq;
    private String userNickname;
    private String userHeadimg;
    private String sex;
    private Float longitude;
    private Float latitude;
    private String userLocation;
    private String realName;
    private Integer userLevel;
    private Integer userScore;
    //我的关注
    private int focus;
    //我的粉丝
    private int fans;
    //我的动态
    private int info;
}
