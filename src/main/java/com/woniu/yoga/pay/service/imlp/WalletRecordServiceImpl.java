package com.woniu.yoga.pay.service.imlp;

import com.woniu.yoga.pay.dao.WalletRecordMapper;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletRecordServiceImpl implements WalletRecordService {
    @Autowired
    private WalletRecordMapper walletRecordMapper;
    @Override
    public int insertRecord(WalletRecord walletRecord) {
     return    walletRecordMapper.insertSelective(walletRecord);
    }
}
