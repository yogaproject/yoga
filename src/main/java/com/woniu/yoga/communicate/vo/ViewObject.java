package com.woniu.yoga.communicate.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description Map封装
 * @Author guochxi
 * @Date 2019/4/23 14:51
 * @Version 1.0
 */
public class ViewObject {

    private Map<String, Object> objs = new HashMap<String, Object>();
    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}
