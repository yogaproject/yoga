package com.woniu.yoga.user.dto;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName CoachDTO
 * @Date 2019/4/30 12:08
 * @Version 1.0
 * @Description 保存从数据库查询得到的瑜伽师的简要信息
 **/
@Data
public class CoachDTO {
    private int userId;
    private String realName;
    private double longitude;
    private double latitude;
    private String headImg;
    //好评率计算
    private int goodComment;
    private int badComment;
    private int commonComment;
    private String CoachStyle;
    private int level;
}
