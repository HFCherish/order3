package com.thoughtworks.ketsu.infrastructure.util.impl;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.util.OrderService;
import com.thoughtworks.ketsu.web.beans.OrderItemRequestBean;
import com.thoughtworks.ketsu.web.beans.OrderRequestBean;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Inject
    ProductRepository productRepository;

    @Override
    public Order createOrder(OrderRequestBean orderRequestBean, User user) {
        List<OrderItemRequestBean> orderItemsInfo = orderRequestBean.getOrderItems();
        if(orderItemsInfo==null || orderItemsInfo.size()==0) {
            throw new IllegalArgumentException("must order at least one prouct.");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequestBean orderItemRequestBean: orderItemsInfo) {
            orderItems.add(createOrderItem(orderItemRequestBean));
        }
        return new Order(orderRequestBean.getName(),
                user.getId(),
                orderRequestBean.getAddress(),
                orderRequestBean.getPhone(),
                orderItems);
    }

    private OrderItem createOrderItem(OrderItemRequestBean orderItemRequestBean) {
        Product product = productRepository.findById(orderItemRequestBean.getProductId())
                .map(product1 -> product1)
                .orElseThrow(() -> new IllegalArgumentException("the ordered product doesn't exist."));
        return new OrderItem(orderItemRequestBean.getProductId(), orderItemRequestBean.getQuantity(), product.getPrice());
    }
}
