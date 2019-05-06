package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Description coach完善信息
 * @Author Administrator
 * @Date 2019/5/6 20:36
 * @Version 1.0
 */
@Data
public class UserCoach {
    private String idcard ;//#身份证 string
    private String realName ;//#真实姓名 string
    private String sex ;//#性别 string
    private String userEmail ;//#邮箱 string
    private String userHeadimg ;//#头像 string
    private String userLocation ;//#教练默认位置 string
    private String userNickname ;//#用户昵称 string
    private String userPhone ;//#用户手机 string
    private int userPrivacy;
    private String userQq ;//#用户qq string（1.1.0新增）
    private String userWechat ;//#用户微信 string（1.1.0新增）
    private int coachStatus;// 是否接课 int
    private int fullClass ;//是否满课 int
    private int coachStyle;// 流派 int
    private String expectedSalary;// 期望薪资 decimal
    private String coachDetail;// 教练介绍 string

}
