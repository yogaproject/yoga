package com.woniu.yoga.home.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Advertisement {
    private Integer adId;

    private String adImg;

    private String adDetail;

    private Integer adSort;

    private BigDecimal adPrice;

    private Integer adFlag;
}