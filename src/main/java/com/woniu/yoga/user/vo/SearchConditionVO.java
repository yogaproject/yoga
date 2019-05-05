package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName SearchConditionVO
 * @Date 2019/4/19 9:37
 * @Version 1.0
 * @Description 学员搜索瑜伽师、场馆的条件
 **/
@Data
public class SearchConditionVO {
    //姓名
    private String realName;
    //位置：经度
    private Float longitude;
    //位置：纬度
    private Float latitude;
    //距离“我”的距离，单位为米
    private int round;
    //教练流派，需要从数据库字典查询，然后选择；场馆无需此项
    private int coachStyle;
    //教练认证方式，值为场馆、平台或不限，其余不合法；场馆无需此项
    private String authentication;
    //教练空闲时间，值为不限、早、中、晚、全天5选一；场馆无需此项
    private String freeTime;

    public SearchConditionVO() {
        round = 10000;
        coachStyle = 0;
        authentication = "不限";
        freeTime = "不限";
    }
}
