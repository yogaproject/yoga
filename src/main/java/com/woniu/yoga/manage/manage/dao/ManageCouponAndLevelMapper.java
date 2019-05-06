package com.woniu.yoga.manage.manage.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ManageCouponAndLevelMapper {
   @Update(" update `user` set user_level=#{1} where user_id = #{0}")
    int updateLevelbyUserid(Integer userid, Integer level);

   @Insert(" insert into user_coupon (uc_id,user_id,coupon_id,uc_flag  )   values (default , #{0}, #{1},default  )")
    int insertCouponbyUserid(Long facevalue, Date eftime, Date extime);

   @Select("select coupon_id from coupon where effective_date= #{0}")
    int findCouponidbyEftime(Date eftime);

   @Insert("insert into user_coupon (uc_id,user_id,coupon_id,uc_flag) values (default , #{0}, #{1},default)")
    int insertCouponUser(Integer couid, Integer userid);
}
