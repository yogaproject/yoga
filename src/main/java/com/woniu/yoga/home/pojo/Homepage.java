package com.woniu.yoga.home.pojo;

import lombok.Data;

import java.io.File;
import java.util.Date;

@Data
public class Homepage {
    private Integer mId;

    private String title;

    private File imageFile;

    private String img;

    private Integer userId;

    private Long commentCount;

    private Date createTime;

    private Integer homepageFlag;

    private String content;
}