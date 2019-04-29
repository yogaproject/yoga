package com.woniu.yoga.pay.service;


import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface WalletService {
    Wallet findWalletByUserId(int userid);

    List<WalletRecord> selectOrderByUserId(int userid);

    int UpdateUserMoneyAndCreateRecord(Map<String, Object> map, HttpServletRequest request);

    int updateUserMoneyByWalletId(int walletId, BigDecimal money);

    int saveMoney(int walletId, BigDecimal money);

    int addBankcardByUserId(Integer userid, String pwd, String againPwd, String bankcard);

    Wallet findWalletByWalletId(int walletId);

    String Unionpaypay(String money,HttpServletRequest req, HttpServletResponse resp);
}
