package com.woniu.yoga.user.myException;

/**
 * @Author liufeng
 * @ClassName IllegalOperationException
 * @Date 2019/4/29 17:00
 * @Version 1.0
 * @Description 自定义异常，等待整合中...
 *              非法操作：主要用于操作非专属的数据
 **/
public class IllegalOperationException  extends  RuntimeException{
    void test()throws Exception{
        try {

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
