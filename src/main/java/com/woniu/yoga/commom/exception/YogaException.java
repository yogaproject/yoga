package com.woniu.yoga.commom.exception;


import com.woniu.yoga.commom.enums.ResultEnum;

/**
 * 自定义异常类
 */
public class YogaException extends RuntimeException{
    private Integer code;

    public YogaException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public YogaException(Integer code,String msg){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
