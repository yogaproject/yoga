package com.woniu.yoga.venue.service.impl;


import com.woniu.yoga.venue.dao.ProductMapper;
import com.woniu.yoga.venue.pojo.Product;
import com.woniu.yoga.venue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> venueAllProductService(int venueId) {
        return productMapper.venueAllProductMapper(venueId);
    }

    @Override
    public int venueAddProductService(Product product) {
        return productMapper.venueAddProductMapper(product);
    }

    @Override
    public int venueDeleteProductService(int productId) {
        return productMapper.venueDeleteProductMapper(productId);
    }

    @Override
    public Product venueFindProduct(int productId) {
        return productMapper.findOneProduct(productId);
    }

    @Override
    public int venueUpdateProduct(Product product) {
        return productMapper.updateByPrimaryKeySelective(product);
    }
}
