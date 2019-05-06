package com.woniu.yoga.commom.utils;

import com.woniu.yoga.commom.exception.YogaException;
import com.woniu.yoga.user.myException.DatabaseException;

/**
 * @Author liufeng
 * @ClassName ExceptionUtil
 * @Date 2019/5/6 9:51
 * @Version 1.0
 * @Description 返回一个异常
 **/
public class ExceptionUtil {
    private static final YogaException DATABASEEXCEPTION = new YogaException(0,"数据库操作出错了");
    //liufeng
    public static YogaException getDatabaseException(){
        return DATABASEEXCEPTION;
    }

}
