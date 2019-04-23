package com.woniu.yoga.pay.controller;
import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletService;
import com.woniu.yoga.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;


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
    public List<WalletRecord> selectOrderByWalletId(int userid ) {
     return  walletService.selectOrderByWalletId(userid);
    }

/**
     * 查询优惠券
     * @param userid
     * @return
     */

    @RequestMapping("/selectcoupon")
    @ResponseBody
    public List<Coupon> selectUserCouponByUserId(int userid ) {
        return  null;
    }





}
