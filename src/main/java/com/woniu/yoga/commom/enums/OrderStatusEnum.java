package com.woniu.yoga.commom.enums;

/**
 * 订单状态枚举类
 * @author guochxi
 */
public enum  OrderStatusEnum {
    NEW(15,"新下单"),
    UNFINISH(16,"未完成"),
    WAIT_TO_COMMENT(17,"待评价"),
    FINISHED(18,"已完成"),
    ;
    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
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
