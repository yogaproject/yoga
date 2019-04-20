package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.pojo.Wallet;
import org.apache.ibatis.annotations.Select;

public interface WalletMapper {
    int deleteByPrimaryKey(Integer walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Integer walletId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);
    @Select("select * from wallet where payer_id = #{payerId}")
    Wallet findByUserId(Integer payerId);
}