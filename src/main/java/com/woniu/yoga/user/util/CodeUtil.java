package com.woniu.yoga.user.util;

import java.util.Random;
import java.util.UUID;

/**
 * @Description: java类作用描述
 * @Author: 路边
 * @time: 2019/4/17 0:19
 */
public class CodeUtil {
    public static String generateUniqueCode(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static String userNumber(){
        //随机生成6位的密码
        String userNumber = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {

            userNumber += random.nextInt(10);
        }
        return userNumber;
    }

}
