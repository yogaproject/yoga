package com.woniu.yoga.commom.enums;

/**
 * 评论实体枚举类
 * @author guochxi
 */
public enum EntityTypeEnum {
    COURSE_ENTITY(7,"课程"),
    HOMEPAGE_ENTITY(8,"主页动态"),
    PRIVATE_MSG(27,"私信"),
    ;
    private Integer code;
    private String msg;
    EntityTypeEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
