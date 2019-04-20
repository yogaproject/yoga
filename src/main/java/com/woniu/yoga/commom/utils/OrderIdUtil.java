package com.woniu.yoga.commom.utils;

import java.util.Random;

/**
 * 订单id生成工具
 */
public class OrderIdUtil {

    public static String getOrderId(){
        return "" + System.currentTimeMillis() + new Random().nextInt(100);
    }
}
