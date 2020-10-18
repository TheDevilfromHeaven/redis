package com.ljh.redis.service;

import com.ljh.redis.dao.Product;
import com.ljh.redis.mapper.ProductMapper;
import com.ljh.redis.utils.RedisUtil;
import jdk.internal.instrumentation.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisUtil.redisList redisList;

    @Autowired
    RedisUtil.redisString redisString;

    public List<Product> searchProduct(){
        List list = new ArrayList<>();
        if (redisUtil.hasKey("productList")){
            System.out.println("从redis中获取数据.");
            list = redisList.get("productList", 0, -1);
        }else {
            list = productMapper.searchProduct();
            System.out.println("从数据库中获取数据");
            System.out.println("从数据库入redis...");
            redisList.set("productList",list);
            System.out.println("成功存入redis.");
        }
        return list;
    }

    public boolean addProduct(String key,Product value){
        boolean set = redisList.set(key, value);
        return set;
    }

    public boolean addString(String key,String value){
        boolean result = redisString.set(key, value);
        return result;
    }

    public String getString(String key){
        String result = (String) redisString.get(key);
        return result;
    }
}
