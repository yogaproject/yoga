package com.woniu.yoga.commom.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description 将对象转换为json
 * @Author Administrator
 * @Date 2019/4/22 21:58
 * @Version 1.0
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
