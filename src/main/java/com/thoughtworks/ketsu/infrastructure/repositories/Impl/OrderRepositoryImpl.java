package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.infrastructure.repositories.OrderRepository;

import java.util.Arrays;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void save(Order order) {

    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(new Order("Imran", "beijing", "5787", Arrays.asList(new OrderItem())));
    }
}
