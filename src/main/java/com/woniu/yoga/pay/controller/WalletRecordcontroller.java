package com.woniu.yoga.pay.controller;

import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/WalletRecord")
public class WalletRecordcontroller {
    private WalletRecordService walletRecordService;

    /**
     * 插入订单
     *
     * @param walletRecord
     */
    public void insertRecord(WalletRecord walletRecord) {
        walletRecordService.insertRecord(walletRecord);
    }

//    @RequestMapping("/OrderRate")
//    public String OrderRate(){
//
//        return null;
//    }
}

