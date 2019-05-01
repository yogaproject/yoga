package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName CoachVO
 * @Date 2019/4/30 12:07
 * @Version 1.0
 * @Description 保存瑜伽师的个人简要信息
 **/
@Data
public class CoachVO {
    private int userId;
    private String realName;
    private double longitude;
    private double latitude;
    private String headImg;
    //好评率
    private float feedback;
    private String CoachStyle;
}
