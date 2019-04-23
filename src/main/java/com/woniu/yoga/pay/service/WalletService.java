package com.woniu.yoga.pay.service;


import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface WalletService {
    Wallet findWalletByUserId(int userid);

    List<WalletRecord> selectOrderByUserId(int userid);

    int UpdateUserMoneyAndCreateRecord(Map<String, Object> map);

    int updateUserMoneyByWalletId(int walletId, BigDecimal money);
}
