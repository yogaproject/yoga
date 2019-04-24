package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.UserAndCoupon;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAndCouponMapper {
    int deleteByPrimaryKey(Integer ucId);

    int insert(UserAndCoupon record);

    int insertSelective(UserAndCoupon record);

    UserAndCoupon selectByPrimaryKey(Integer ucId);

    int updateByPrimaryKeySelective(UserAndCoupon record);

    int updateByPrimaryKey(UserAndCoupon record);
}