package com.woniu.yoga.user.vo;

/**
 * @Author liufeng
 * @ClassName StudentVO
 * @Date 2019/4/22 14:55
 * @Version 1.0
 * @Description 记录学生的简要信息
 **/
public class StudentVO {
    //    昵称
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "{" +
                "nickName:'" + nickName + '\'' +
                '}';
    }
}
