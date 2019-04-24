package com.woniu.yoga.venue.dao;

import com.woniu.yoga.venue.pojo.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    //根据场馆id ，展示场馆全部产品信息
    List<Product> venueAllProductMapper(int venueId);

    //场馆增加产品
    int venueAddProductMapper(Product product);

    //场馆删除产品
    int venueDeleteProductMapper(Integer productId);



}