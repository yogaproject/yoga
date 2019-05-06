package com.woniu.yoga.user.dto;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName UserDTO
 * @Date 2019/5/5 17:43
 * @Version 1.0
 * @Description 用户的关注，粉丝，动态，评论
 **/
@Data
public class UserDTO {
    private int focus;
    private int fans;
    private int info;
    private int comments;
}
