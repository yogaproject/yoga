package com.woniu.yoga.venue.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Product {
    private Integer productId;

    private Integer venueId;

    private String productType;

    private BigDecimal productPrice;

    private String productDetail;

    private Integer totalTime;

    private Integer productLevel;

    private Date startTime;

    private Date endTime;

    private String consumeTime;

    private Integer productFlag;


}