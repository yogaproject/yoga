package com.woniu.yoga.manage.manage.service;

import com.woniu.yoga.manage.manage.dao.CancelOrderMapper;
import com.woniu.yoga.user.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CancelOrderImpl implements CancelOrderMapper {
    @Autowired
    static CancelOrderMapper dao;

    @Override
    public Order selectAimOrder(String orderid) {
        return dao.selectAimOrder(orderid);
    }

    @Override
    public int updateBackToPay(Integer payerid, BigDecimal money) {
        return dao.updateBackToPay(payerid,money);
    }

    @Override
    public int updateReduceAccepter(Integer acceptid, BigDecimal money) {
        return dao.updateReduceAccepter(acceptid,money);
    }

    @Override
    public int insertWalletrecoredCancelRecord(Integer payerid, Integer acceptid, BigDecimal money) {
        return dao.insertWalletrecoredCancelRecord(payerid,acceptid,money);
    }

    @Override
    public int updateOrderPaystatue(String orderid) {
        return dao.updateOrderPaystatue(orderid);
    }
}
