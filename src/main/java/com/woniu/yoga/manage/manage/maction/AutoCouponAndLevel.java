package com.woniu.yoga.manage.manage.maction;
//import com.woniu.yoga.manage.service.ManageCouponAndLevel;

import com.woniu.yoga.manage.manage.service.ManageCouponAndLevelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Component
public class AutoCouponAndLevel {
   @Autowired
    public ManageCouponAndLevelImpl service;

    /*public ManageCouponAndLevelImpl getService() {
        return service;
    }

    public void setService(ManageCouponAndLevelImpl service) {
        this.service = service;
    }*/




    public String autoGiveLevelAndCoupon(Long nowmoney, Long aftermoney, Integer nowscore, Integer afterscore, Integer userid){

        Long paid=aftermoney-nowmoney;
        int scoreincrease=afterscore-nowscore;
        if (paid>0&&scoreincrease>0){  //总判断，即充值和积分操作都完成
            if (afterscore>=1400){  //等级3操作

                if (nowscore<1400){
                    int level=3;
                    long facevalue=100;
                    System.out.println(userid+","+level);
                    int lflag=  service.updateLevelbyUserid(userid,level);

                   if (lflag==1) {
                       Integer giveback = giveBenefit(facevalue);
                           if (!(giveback == 0)) {
                           int couponid = giveback;
                           int cuflag = service.insertCouponUser(couponid, userid);
                               System.out.println("is"+cuflag);
                                if (cuflag == 1) {
                                   return "等级和福利操作成功";
                                   }else{
                                          return "福利没有和用户连接";
                                     }

                       } else {
                           return "发放和寻找福利券操作失败";
                       }
                   }else{
                       return "升到三级操作失败";
                   }

                }else{
                    //本来已达到等级3，发放福利
                    long facevalue=60;


                    Integer giveback = giveBenefit(facevalue);
                    if (!(giveback == 0)) {
                        int couponid = giveback;
                        int cuflag = service.insertCouponUser(couponid, userid);
                        if (cuflag == 1) {
                            return "操作成功";
                        }else{
                            return "福利没有和用户连接";
                        }

                    } else {
                        return "发放和寻找福利券操作失败";
                    }
                }
            }//等级3操作
            if (afterscore>=900){  //等级2操作
                if (nowscore<900){
                    //通过本次充值才达到等级2，提升等级
                    int level=2;
                    int lflag=  service.updateLevelbyUserid(userid,level);
                    if (lflag==1){
                        return "操作成功";
                    }
                    return "升到二级操作失败";
                }else{
                    return "操作成功" ;                                    //本来已达到等级2,还未达到等级3

                }
            }//等级2操作
            if (afterscore>=500){  //等级1操作
                if (nowscore<500){
                    //通过本次充值才达到等级1，提升等级
                    int level=1;
                    int lflag=0;
                     lflag= service.updateLevelbyUserid(userid,level);
                    if (lflag==1){
                        return "操作成功";
                    }
                    return "升到一级操作失败";
                }else{
                    return "操作成功" ;                                   //本来已达到等级1,还未达到等级2
                }
            }//等级1操作


        }   //总判断，即充值和积分操作都完成

        return "充值或积分操作异常";


    }

    public Integer giveBenefit(long facevalue){
        Date date=    new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String nowtime  =dateFormat.format(date);
        int cflag=0;
        int couid=0;
        try {
            Date utileftime=     dateFormat.parse(nowtime);
            Calendar ca=Calendar.getInstance();
            ca.setTime(utileftime);
            ca.add(Calendar.DATE,10);
            Date utilextime=  ca.getTime();
        java.sql.Date eftime= new java.sql.Date(utileftime.getTime());
        java.sql.Date extime=  new java.sql.Date(utilextime.getTime());
            cflag=   service.insertCouponbyUserid(facevalue, eftime, extime);
            couid     =   service.findCouponidbyEftime(eftime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (cflag==1&&!(couid==0)){
            return  couid;
        }else
        return 0;
    }


    }


