package com.woniu.yoga.pay.service.imlp;

import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.dao.WalletMapper;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl  implements WalletService {
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public Wallet findWalletByUserId(int userid) {
        return walletMapper.findWalletByUserId(userid);
    }

    @Override
    public void updateBalanceByWalletId(int walletid, double balance) {
        walletMapper.updateBalanceByWalletId(walletid,balance);
    }

    @Override
    public List<WalletRecord> selectOrderByWalletId(int userid) {
        return walletMapper.selectOrderByWalletId(userid);
    }


}
