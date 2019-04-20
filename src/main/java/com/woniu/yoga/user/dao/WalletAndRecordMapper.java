package com.woniu.yoga.user.dao;

import com.woniu.yoga.sign.entity.WalletAndRecord;

public interface WalletAndRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(WalletAndRecord record);

    int insertSelective(WalletAndRecord record);

    WalletAndRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(WalletAndRecord record);

    int updateByPrimaryKey(WalletAndRecord record);
}