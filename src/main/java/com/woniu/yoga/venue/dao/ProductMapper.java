package com.woniu.yoga.venue.dao;

import com.woniu.yoga.venue.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(int productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(int productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    //根据场馆id ，展示场馆全部产品信息
    List<Product> venueAllProductMapper(@Param("venueId") int venueId);

    //查询单个产品信息
    Product findOneProduct(@Param("productId") int productId);

    //场馆增加产品
    int venueAddProductMapper(Product product);

    //场馆删除产品
    int venueDeleteProductMapper(int productId);



}