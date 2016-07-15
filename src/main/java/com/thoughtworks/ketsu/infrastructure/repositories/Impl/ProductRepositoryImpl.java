package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public void save(Product product) {

    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(new Product());
    }
}
