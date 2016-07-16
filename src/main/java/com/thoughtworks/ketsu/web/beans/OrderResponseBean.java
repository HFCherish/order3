package com.thoughtworks.ketsu.web.beans;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderResponseBean extends OrderBaseResponseBean{
    public OrderResponseBean(Order order) {
        super(order);
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        Map res = super.toRefJson(routes);
        List orderItemsInfo = new ArrayList();
        for(OrderItem orderItem: order.getOrderItems()) {
            orderItemsInfo.add(new OrderItemResponseBean(orderItem).toJson(routes));
        }
        res.put("order_items", orderItemsInfo);
        return res;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
