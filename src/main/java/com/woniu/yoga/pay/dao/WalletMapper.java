package com.woniu.yoga.pay.dao;

import com.woniu.yoga.pay.pojo.Wallet;
import org.apache.ibatis.annotations.Select;

public interface WalletMapper {
    int deleteByPrimaryKey(Integer walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Integer walletId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);
    @Select("select * from wallet where user_id =#{userId}")
    Wallet selectByUserId(Integer userId);
}