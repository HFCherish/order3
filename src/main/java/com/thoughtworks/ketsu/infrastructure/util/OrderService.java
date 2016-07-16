package com.thoughtworks.ketsu.infrastructure.util;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.web.beans.OrderRequestBean;

public interface OrderService {
    public Order createOrder(OrderRequestBean orderRequestBean, User user);
}
