package com.woniu.yoga.user.dto;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName InteractionDTO
 * @Date 2019/5/5 9:06
 * @Version 1.0
 * @Description
 **/
@Data
public class InteractionDTO {
    //我的关注
    private int focus;
    //我的粉丝
    private int fans;
    //我的动态
    private int info;
    //我的评论
    private int comments;
}
