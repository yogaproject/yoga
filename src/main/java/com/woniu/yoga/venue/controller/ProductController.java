package com.woniu.yoga.venue.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.venue.pojo.Product;
import com.woniu.yoga.venue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //根据场馆id，展示场馆所有卡的详细信息
    @RequestMapping("/venueAllProduct")
    @ResponseBody
    public List<Product> venueAllProduct(Integer venueId){
        List productList = productService.venueAllProductService(venueId);
        return  productList;
    }

    //增删查改产品的详细信息
    //1.增
    @RequestMapping("/venueAddProduct")
    @ResponseBody
    public Result venueAddProduct(Product product){
        int num = productService.venueAddProductService(product);
        if (num == 0){
            return Result.error("产品添加失败");
        }
        return Result.success("产品添加成功");
    }
    //2.删
    @RequestMapping("/venueDeleteProduct")
    @ResponseBody
    public Result venueDeleteProduct(Integer productId){
        int num = productService.venueDeleteProductService(productId);
        if (num == 0){
            return Result.error("产品删除失败");
        }
        return  Result.success("产品删除成功");
    }
    //3.查
    @RequestMapping("/venueSelectProduct")
    @ResponseBody
    public Product venueSelectProduct(Integer productId){
        System.out.println(productId);
        Product product = productService.venueFindProduct(productId);
        System.out.println(product);
        return product;
    }
    //4.改
    @RequestMapping("/venueUpdateProduct")
    @ResponseBody
    public Result venueUpdateProduct(Product product){
        int num = productService.venueUpdateProduct(product);
        if (num == 0){
            return Result.error("产品修改失败");
        }
        return Result.success("产品修改成功");
    }






}
