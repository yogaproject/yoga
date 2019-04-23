package com.woniu.yoga.pay.service.imlp;

import com.woniu.yoga.commom.utils.Attributes;
import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.dao.WalletMapper;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletRecordService;
import com.woniu.yoga.pay.service.WalletService;

import com.woniu.yoga.user.pojo.User;
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
        int allmoney = Integer.parseInt(map.get("allmoney").toString()) ;
        //根据用户id,找到对应钱包
        Wallet wallet =walletMapper.findWalletByUserId(user.getUserId());
        //修改钱包余额
        int updatResult = walletMapper.updateUserMoneyByWalletId(wallet.getWalletId(),new BigDecimal(allmoney));
        WalletRecord walletRecord =new WalletRecord();
        int m= walletRecordService.insertRecord(walletRecord);
        if (updatResult>=0 && m>0){
            return 200;
        }
        return 0;
    }

    @Override
    public int updateUserMoneyByWalletId(int walletId, BigDecimal money) {
        return walletMapper.updateUserMoneyByWalletId(walletId,money);
    }


}
