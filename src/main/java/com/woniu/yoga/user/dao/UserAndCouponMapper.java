package com.woniu.yoga.user.dao;

import com.woniu.yoga.sign.entity.UserAndCoupon;

public interface UserAndCouponMapper {
    int deleteByPrimaryKey(Integer ucId);

    int insert(UserAndCoupon record);

    int insertSelective(UserAndCoupon record);

    UserAndCoupon selectByPrimaryKey(Integer ucId);

    int updateByPrimaryKeySelective(UserAndCoupon record);

    int updateByPrimaryKey(UserAndCoupon record);
}