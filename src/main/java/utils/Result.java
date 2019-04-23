package utils;

/**
 * JSON数据响应结果的封装类
 */
public class Result {
    /**
     * 返回结果的状态码 0 表示成功
     */
    private Integer code;
    /**
     * 返回消息数据
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result ok(){
        return new Result(0);
    }

    public static Result ok(String msg){
        return new Result(0,msg);
    }

    public static Result ok(Object data){
        return new Result(0,data);
    }

    public static Result error(){
        return new Result(500);
    }

    public static Result error(String msg){
        return new Result(500,msg);
    }
}
