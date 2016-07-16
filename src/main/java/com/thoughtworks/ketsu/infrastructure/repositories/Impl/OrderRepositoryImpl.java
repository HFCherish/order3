package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.OrderRepository;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Inject
    OrderMapper orderMapper;

    @Override
    public void save(Order order) {
        orderMapper.save(order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orderMapper.findById(id));
    }
}
