package com.woniu.yoga.manage.manage.service;

import com.woniu.yoga.manage.manage.dao.ManageCouponAndLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

//import com.woniu.yoga.manage.service.ManageCouponAndLevel;

@Service
public class ManageCouponAndLevelImpl implements ManageCouponAndLevelMapper {
    @Autowired
  static ManageCouponAndLevelMapper dao;

    /*public static ManageCouponAndLevelMapper getDao() {
        return dao;
    }

    public static void setDao(ManageCouponAndLevelMapper dao) {
        ManageCouponAndLevelImpl.dao = dao;
    }*/

    @Override
    //@Test
    public int updateLevelbyUserid(Integer userid, Integer level) {
        System.out.println("获取到"+userid+level);
        System.out.println("service层");
        return dao.updateLevelbyUserid(userid,level);
    }

    @Override
    public int insertCouponbyUserid(Long facevalue, Date eftime, Date extime) {
        return dao.insertCouponbyUserid(facevalue,eftime,extime);
    }

    @Override
    public int findCouponidbyEftime(Date eftime) {
        return dao.findCouponidbyEftime(eftime);
    }

    @Override
    public int insertCouponUser(Integer couid, Integer userid) {
        return dao.insertCouponUser(couid,userid);
    }
}
