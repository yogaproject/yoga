package com.woniu.yoga.pay.service.imlp;

import com.woniu.yoga.commom.utils.Attributes;
import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.dao.WalletMapper;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletRecordService;
import com.woniu.yoga.pay.service.WalletService;

import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.UserService;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class WalletServiceImpl  implements WalletService {
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private WalletRecordService walletRecordService;
    @Autowired
    private UserService userService;

    @Override
    public Wallet findWalletByUserId(int userid) {
        return walletMapper.findWalletByUserId(userid);
    }

    @Override
    public List<WalletRecord> selectOrderByUserId(int userid) {
        return walletMapper.selectOrderByUserId(userid);
    }

    @Override
    public int UpdateUserMoneyAndCreateRecord(Map<String, Object> map, HttpServletRequest request) {
        User user =(User) map.get(Attributes.CURRENT_USER);
        int allmoney = Integer.parseInt(map.get("total_amount").toString()) ;
        //根据用户id,找到对应钱包
        Wallet wallet =walletMapper.findWalletByUserId(user.getUserId());
        //修改钱包余额 充值
        int updatResult = walletMapper.updateUserMoneyByWalletId(wallet.getWalletId(),new BigDecimal(allmoney));
        WalletRecord walletRecord =new WalletRecord();
        walletRecord.setFromId(user.getUserId());
        walletRecord.setMoney(new BigDecimal(allmoney) );
        walletRecord.setPayType(0);
        walletRecord.setToId(Integer.parseInt(map.get("app_id").toString()));
        walletRecord.setWalletId(wallet.getWalletId());
        int m= walletRecordService.insertRecord(walletRecord);

        if (updatResult>=0 && m>0){
            return 200;
        }
        return -1;
    }

    @Override
    public int updateUserMoneyByWalletId(int walletId, BigDecimal money) {
        return walletMapper.updateUserMoneyByWalletId(walletId,money);
    }

    @Override
    public int saveMoney(int walletId, BigDecimal money) {
        return walletMapper.saveMoney(walletId,money);
    }


}
