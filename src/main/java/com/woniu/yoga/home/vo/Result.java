package com.woniu.yoga.home.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 返回数据
 * @date 2019/4/30 11:58
 */
@Data
@ApiModel("返回数据类")
public class Result<T> {

    @ApiModelProperty("返回状态")
    private  Integer  code ;

    @ApiModelProperty("返回信息")
    private String  message;

    @ApiModelProperty("返回对象")
    private T  data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result success(String message,Object data){
        return new Result(1, message, data);
    }

    public static Result success(String message){
        return new Result(1, message);
    }

    public static Result error(String message){
        return new Result(0, message);
    }
}
