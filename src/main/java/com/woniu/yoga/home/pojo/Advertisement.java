package com.woniu.yoga.home.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("广告")
public class Advertisement {
    @ApiModelProperty("id")
    private Integer adId;

    @ApiModelProperty("图片")
    private String adImg;

    @ApiModelProperty("详情")
    private String adDetail;

    @ApiModelProperty("排序")
    private Integer adSort;

    @ApiModelProperty("广告费用")
    private BigDecimal adPrice;

    @ApiModelProperty("软删除")
    private Integer adFlag;
}