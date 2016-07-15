package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import javax.inject.Inject;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Inject
    ProductMapper productMapper;

    @Override
    public void save(Product product) {
        productMapper.save(product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productMapper.findById(id));
    }
}
