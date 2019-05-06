package com.woniu.yoga.pay.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawDepositVo {
    private Integer userId;
    private String account;
    private String pwd;
    private BigDecimal money;

    @Override
    public String toString() {
        return "WithdrawDepositVo{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", money=" + money +
                '}';
    }
}
