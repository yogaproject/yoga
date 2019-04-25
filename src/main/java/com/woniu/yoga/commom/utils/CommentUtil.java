package com.woniu.yoga.commom.utils;

import java.util.Date;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 判断评论或图文发布时间
 * @date 2019/4/23 19:47
 */
public class CommentUtil {
    public static String publishTime(Date date){
        long longDate = date.getTime();

        long ms = System.currentTimeMillis() - longDate;

        int minutes = (int) (ms / 60000);

        int hour = minutes / 60;

        int day = hour / 24;

        int month = day / 30;

        String publish;
        if (minutes == 0) {
            publish = ("刚刚发布");
        } else if (hour == 0) {
            publish = (minutes + "分钟前");
        } else if (hour > 0 && hour < 24) {
            publish = (hour + "小时前");
        } else if (day >= 1 && day < 30) {
            publish = (day + "天前");
        } else if (month > 1 && month < 12) {
            publish = (month + "个月前");
        } else {
            publish = String.valueOf(date);
        }
        return publish;
    }
}
