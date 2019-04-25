package com.woniu.yoga.commom.utils;

/**
 * @Author liufeng
 * @ClassName UserLevelUtil
 * @Date 2019/4/24 19:23
 * @Version 1.0
 * @Description 根据用户积分计算会员等级
 **/
public class UserLevelUtil {
    public static int getUserLevel(Integer integral) {
        if (integral >= 0 && integral < 300) {
            return 1;
        }
        if (integral >= 300 && integral < 800) {
            return 2;
        }
        if (integral >= 800 && integral < 2000) {
            return 3;
        }
        if (integral >= 2000 && integral < 5000) {
            return 4;
        }
        if (integral >= 5000 && integral < 10000) {
            return 5;
        }
        if (integral >= 10000 && integral < 20000) {
            return 6;
        }
        if (integral >= 20000 && integral < 50000) {
            return 7;
        }
        if (integral >= 50000) {
            return 8;
        }
        return 0;
    }
}
