package com.woniu.yoga.commom.enums;

/**
 * 结果枚举类，用于统一管理错误码和对应的信息
 * @author guochxi
 */
public enum  ResultEnum {
    UNKNOWN_ERROR(-1,"异常不详"),
    PARAM_ERROR(1,"参数不正确"),;
    private Integer code;
    private String msg;

    ResultEnum(Integer code,String msg){
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
