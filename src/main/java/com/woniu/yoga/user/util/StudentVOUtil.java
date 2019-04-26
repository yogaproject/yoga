package com.woniu.yoga.user.util;

import com.woniu.yoga.user.vo.StudentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentVOUtil
 * @Date 2019/4/26 14:51
 * @Version 1.0
 * @Description test
 **/
public class StudentVOUtil {

    public static List getStudents(){
        List list = new ArrayList();
        StudentVO s1 = new StudentVO();
        s1.setNickName("zhangshan");
        s1.setHeadImg("user.jpg");
        StudentVO s2 = new StudentVO();
        s2.setHeadImg("s2.jpg");
        s2.setNickName("lishi");
        list.add(s1);
        list.add(s2);
        return  list;
    }



}
