package com.ljh.redis.mapper;

import com.ljh.redis.dao.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product")
    List<Product> searchProduct();
}
