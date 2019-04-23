package com.woniu.yoga.venue.service;


import com.woniu.yoga.venue.pojo.Product;

import java.util.List;

public interface ProductService {

    //根据场馆id，展示场馆所有卡的信息
    List<Product> venueAllProductService(Integer venueId);

    //场馆增加产品
    int venueAddProductService(Product product);

    //场馆删除产品
    int venueDeleteProductService(Integer productId);

    //场馆查询产品
    Product venueFindProduct(Integer productId);

    //场馆修改产品
    int venueUpdateProduct(Product product);
}
