package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Author liufeng
 * @ClassName Result
 * @Date 2019/4/18 16:06
 * @Version 1.0
 * @Description 常用的返回值类型，包含状态码code、描述信息message和数据Object
 **/
@Data
public class Result {
    //code值通常为0,1,0为假，1为真
    private Integer code;
    //描述信息，一般为判定不通过的原因
    private String message;
    //附带的数据，无论是单条还是多条数据，都以集合或数组返回，名称为data
    private Object data;


    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

}
