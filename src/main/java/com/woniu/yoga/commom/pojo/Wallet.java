package com.woniu.yoga.commom.pojo;

public class Wallet {
    private Integer walletId;

    private Integer userId;

    private Long balance;

    private Long totalCost;

    private String bankcard;

    private Integer payPwd;

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public Integer getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(Integer payPwd) {
        this.payPwd = payPwd;
    }
}