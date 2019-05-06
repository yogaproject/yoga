package com.woniu.yoga.home.service;

import com.woniu.yoga.home.pojo.Advertisement;
import com.woniu.yoga.home.vo.Result;

import java.util.List;

public interface AdvertisementService {
    Result<List<Advertisement>> showAdvertisement();
}
