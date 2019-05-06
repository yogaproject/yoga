package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Description 用户简要信息
 * @Author Administrator
 * @Date 2019/5/5 11:28
 * @Version 1.0
 */
@Data
public class UserVO {
    private int userId;
    private String img;
    private String nickname;
    private int level;
}
