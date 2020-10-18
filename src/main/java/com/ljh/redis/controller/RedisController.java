package com.ljh.redis.controller;

import com.ljh.redis.dao.Product;
import com.ljh.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    ProductService productService;

    @PostMapping("/test")
    @ResponseBody
    public List<Product> testRedis(){
        return productService.searchProduct();
    }

    @PostMapping("/add")
    @ResponseBody
    public Object testAdd(int productId,String productCategories){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductCategories(productCategories);
        return productService.addProduct("productList", product);
    }

    @PostMapping("/addString")
    @ResponseBody
    public Object AddString(String key,String value){
        return productService.addString(key,value);
    }

    @GetMapping("/getString")
    @ResponseBody
    public String getString(String key){
        return productService.getString(key);
    }
}
