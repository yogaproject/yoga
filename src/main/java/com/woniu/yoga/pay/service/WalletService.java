package com.woniu.yoga.pay.service;


import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;

import java.util.List;

public interface WalletService {
    Wallet findWalletByUserId(int userid);

    void updateBalanceByWalletId(int walletid, double balance);

    List<WalletRecord> selectOrderByWalletId(int walletid);


}
