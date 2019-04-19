package com.woniu.yoga.commom.dao;

import com.woniu.yoga.commom.pojo.WalletRecord;

public interface WalletRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(WalletRecord record);

    int insertSelective(WalletRecord record);

    WalletRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(WalletRecord record);

    int updateByPrimaryKey(WalletRecord record);
}