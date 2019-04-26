package com.woniu.yoga.user.vo;

import java.io.Serializable;

/**
 * @Author liufeng
 * @ClassName StudentVO
 * @Date 2019/4/22 14:55
 * @Version 1.0
 * @Description 记录学生的简要信息
 **/
public class StudentVO implements Serializable {
    //昵称
    private String nickName;
    //头像
    private String headImg;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
