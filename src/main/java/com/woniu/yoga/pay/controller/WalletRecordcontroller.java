package com.woniu.yoga.pay.controller;

import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Record")
public class WalletRecordcontroller {
    private WalletRecordService walletRecordService;

    /**
     * 插入订单
     * @param walletRecord
     */
    public void insertRecord(WalletRecord walletRecord){
       walletRecordService.insertRecord(walletRecord);
    }
}
