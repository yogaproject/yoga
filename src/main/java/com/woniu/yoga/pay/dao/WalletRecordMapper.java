package com.woniu.yoga.pay.dao;

import com.woniu.yoga.pay.pojo.WalletRecord;

public interface WalletRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(WalletRecord record);

    int insertSelective(WalletRecord record);

    WalletRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(WalletRecord record);

    int updateByPrimaryKey(WalletRecord record);
}