package com.woniu.yoga.pay.service;

import com.woniu.yoga.pay.pojo.WalletRecord;

public interface WalletRecordService {
    //插入订单
    int insertRecord(WalletRecord walletRecord);
}
