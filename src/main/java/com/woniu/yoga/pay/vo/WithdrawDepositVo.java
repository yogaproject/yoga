package com.woniu.yoga.pay.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawDepositVo {
    private Integer userId;
    private String account;
    private String pwd;
    private BigDecimal money;
}
