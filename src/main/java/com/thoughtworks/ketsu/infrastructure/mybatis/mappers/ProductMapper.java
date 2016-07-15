package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    Product findById(@Param("id") String id);

    void save(@Param("product") Product product);

    List<Product> findAll();
}
