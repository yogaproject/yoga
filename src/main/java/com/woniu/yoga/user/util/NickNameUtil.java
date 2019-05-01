package com.woniu.yoga.user.util;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * @Description: java类作用描述nickNameUtil
 * @Author: lxy
 * @time: 2019/4/24 16:28
 */
public class NickNameUtil {
    public static String getRandomNickName() {
        Random num=new Random();
       int numCount=num.nextInt(4)+1;
        String ret = "";
        for (int i = 0; i < numCount; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        HashSet<String> set = new HashSet<String>();
        String chineseName =  ret;
        if (!set.contains(chineseName)) {
            set.add(chineseName);
        }
        Iterator<String> iterator = set.iterator();
        String nickName="";
        nickName=iterator.next() + "\n";
        return nickName;
    }
}
