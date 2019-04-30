package com.woniu.yoga.manage.dao;

import com.woniu.yoga.manage.pojo.Coupon;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface CouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
    //liufeng 查询可用的优惠券（用户名，生效时间，过期时间，状态为可用）
    List<Coupon> selectByUserId(Integer userId) throws SQLException;
}