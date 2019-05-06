package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName VenueVOR
 * @Date 2019/4/30 14:04
 * @Version 1.0
 * @Description 保存场馆的简要信息，因为VO重名了，所以加上R标识
 **/
@Data
public class VenueVOR {
    private Integer userId;
    private String realName;
    private double longitude;
    private double latitude;
    private String headImg;
    //点击量
    private Integer clicks;
    //瑜伽师数量
    private int coachCount;


}
