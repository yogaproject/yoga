package com.woniu.yoga.manage.dao;

import com.woniu.yoga.manage.pojo.Advertisement;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(Integer adId);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(Integer adId);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
}