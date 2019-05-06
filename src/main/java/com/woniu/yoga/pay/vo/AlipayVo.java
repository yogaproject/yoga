package com.woniu.yoga.pay.vo;

import lombok.Data;

@Data
public class AlipayVo {
    private String allmoney;
    private String goodsIds;
    private String goodsprice;
    private String goodscount;

    @Override
    public String toString() {
        return "AlipayVo{" +
                "allmoney='" + allmoney + '\'' +
                ", goodsIds='" + goodsIds + '\'' +
                ", goodsprice='" + goodsprice + '\'' +
                ", goodscount='" + goodscount + '\'' +
                '}';
    }
}
