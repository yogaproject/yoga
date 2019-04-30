package com.woniu.yoga.user.dto;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName SearchConditionDTO
 * @Date 2019/4/19 10:04
 * @Version 1.0
 * @Description TODO
 **/
@Data
public class SearchConditionDTO {
    //姓名
    private String realName;
    //位置边界：东方经度
    private double eastLongitude;
    //位置边界：西方经度
    private double westLongitude;
    //位置边界：北方纬度
    private double nothLatitude;
    //位置边界：南方纬度
    private double southLatitude;
    //教练流派，需要从数据库字典查询，然后选择；场馆无需此项
    private int coachStyle;
    //教练认证方式，值为“不限”、“场馆认证”或者“平台认证”，其余不合法；场馆无需此项
    private Integer authentication;
    //教练空闲时间：起始时间——结束时间，格式"hh:dd"；场馆无需此项
    private String freeTimeStart;
    private String freeTimeEnd;

}
