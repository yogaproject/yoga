package com.woniu.yoga.crowdfunding.dao;

import com.woniu.yoga.crowdfunding.pojo.Supporter;
import com.woniu.yoga.crowdfunding.vo.MySupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupporterMapper {

    List<Supporter> querySupByIds(Supporter supporter);

    int deleteByPrimaryKey(Integer supId);

    int insert(Supporter record);

    int insertSelective(Supporter record);

    Supporter selectByPrimaryKey(Integer supId);

    int updateByPrimaryKeySelective(Supporter record);

    int updateByPrimaryKey(Supporter record);

    List<MySupVO> selMySup(Integer userId);
}