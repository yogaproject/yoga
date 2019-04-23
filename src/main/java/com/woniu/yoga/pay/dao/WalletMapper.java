package com.woniu.yoga.pay.dao;

import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WalletMapper {
    int deleteByPrimaryKey(Integer walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Integer walletId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);
    //查找钱包
    @Select("select * from wallet where user_id=#{userid}")
    Wallet findWalletByUserId(@Param("userid") int userid);

    //更新钱包余额
    @Update("update wallet set balance=#{balance} where wallet_id=#{walletid}")
    void updateBalanceByWalletId(@Param("walletid") int walletid,@Param("balance") double balance);

    //查询交易记录
    @Select("select * from wallet_record where from_id =#{userid}")
    List<WalletRecord> selectOrderByWalletId(@Param("userid") int userid);
}