package com.woniu.yoga.communicate.vo;

import lombok.Data;

import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 我的主页面
 * @date 2019/5/4 21:27
 */
@Data
public class MyVo {
    private Integer fansCount;

    private Integer followCount;

    private String userNickName;

    private String userHeadimg;

    private Integer userLevel;

    private Integer homepageCount;
}
