package com.woniu.yoga.controller;


import com.woniu.yoga.pojo.User_coupon;
import com.woniu.yoga.pojo.Wallet;
import com.woniu.yoga.pojo.Wallet_record;
import com.woniu.yoga.service.WalletService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    /**
     * 根据user_id查找钱包
     *
     * @param userid
     * @return Wallet
     */
    @RequestMapping("/selectwallet")
    @ResponseBody
    public Wallet findWalletByUserId(int userid) {
        return walletService.findWalletByUserId(userid);
    }

    /**
     * 更新钱包余额
     * @param walletid
     * @param balance
     */
    @RequestMapping("/updatebalance")
    @ResponseBody
    public void updateBalanceByWalletId(int walletid ,double balance) {
       walletService.updateBalanceByWalletId(walletid,balance);
    }

    /**
     * 查询交易记录
     * @param userid
     * @return
     */
    @RequestMapping("/selectorder")
    @ResponseBody
    public List<Wallet_record> selectOrderByWalletId(int userid ) {
     return  walletService.selectOrderByWalletId(userid);
    }

    /**
     * 查询优惠券
     * @param userid
     * @return
     */
    @RequestMapping("/selectcoupon")
    @ResponseBody
    public List<User_coupon> selectUserCouponByUserId(int userid ) {
        return  walletService.selectUserCouponByUserId(userid);
    }





}
