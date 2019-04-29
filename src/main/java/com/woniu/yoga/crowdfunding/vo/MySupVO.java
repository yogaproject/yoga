package com.woniu.yoga.crowdfunding.vo;

import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;

import java.math.BigDecimal;

/**
 * @Project yoga
 * @Description 一个user封装实体，返回所支持的项目及为每个项目支援的总金额
 * @Author HanFeng
 * @Create in 2019/4/23 10:40
 */
public class MySupVO {

    private Integer cfId;
    private BigDecimal SupMoney;
    private CrowdFunding crowdFunding;

    public Integer getCfId() {
        return cfId;
    }

    public void setCfId(Integer cfId) {
        this.cfId = cfId;
    }

    public BigDecimal getSupMoney() {
        return SupMoney;
    }

    public void setSupMoney(BigDecimal supMoney) {
        SupMoney = supMoney;
    }

    public CrowdFunding getCrowdFunding() {
        return crowdFunding;
    }

    public void setCrowdFunding(CrowdFunding crowdFunding) {
        this.crowdFunding = crowdFunding;
    }

    @Override
    public String toString() {
        return "MySupVO{" +
                "cfId=" + cfId +
                ", SupMoney=" + SupMoney +
                ", crowdFunding=" + crowdFunding +
                '}';
    }
}
