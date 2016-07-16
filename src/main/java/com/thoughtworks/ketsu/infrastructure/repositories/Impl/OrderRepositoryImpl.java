package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderItemMapper;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.OrderRepository;

import javax.inject.Inject;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Inject
    OrderMapper orderMapper;

    @Inject
    OrderItemMapper orderItemMapper;

    @Override
    public void save(Order order) {
        orderMapper.save(order);
        for(OrderItem orderItem: order.getOrderItems()) {
            orderItemMapper.save(orderItem, order.getId());
        }
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orderMapper.findById(id));
    }
}
