package com.woniu.yoga.home.service.impl;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.home.dao.AdvertisementMapper;
import com.woniu.yoga.home.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 广告业务层
 * @date 2019/4/29 17:40
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    @Cacheable(value = "advertisement", key = "#advertisement.adId")
    public Result showAdvertisement() {
        return Result.success("成功",advertisementMapper.queryAdvertisement());
    }
}
