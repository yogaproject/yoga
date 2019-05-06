package com.woniu.yoga.home.dao;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.home.pojo.Advertisement;

import java.util.List;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(Integer adId);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(Integer adId);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);

    List<Advertisement> queryAdvertisement();
}