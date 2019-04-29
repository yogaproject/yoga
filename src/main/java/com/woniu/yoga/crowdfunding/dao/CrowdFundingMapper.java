package com.woniu.yoga.crowdfunding.dao;

import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import com.woniu.yoga.crowdfunding.pojo.Supporter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CrowdFundingMapper {
    int deleteByPrimaryKey(Integer cfId);

    int insert(CrowdFunding record);

    int insertSelective(CrowdFunding record);

    CrowdFunding selectByPrimaryKey(Integer cfId);

    CrowdFunding selOneCf(Integer cfId);

    int updateByPrimaryKeySelective(CrowdFunding record);

    int updateByPrimaryKey(CrowdFunding record);

    ArrayList<CrowdFunding> getCfs(CrowdFunding crowdFunding, @Param("orderCondition") String orderCondition, @Param("orderType") String orderType);

    int updateCurMoney(Supporter supporter);

    int updateSupCount(Supporter supporter);

    List<CrowdFunding> getMyFoc(Integer userId);

    int insertFoc(@Param("userId") Integer userId, @Param("cfId") Integer cfId);

    int updateFoc(@Param("userId") Integer userId, @Param("cfId") Integer cfId);
}