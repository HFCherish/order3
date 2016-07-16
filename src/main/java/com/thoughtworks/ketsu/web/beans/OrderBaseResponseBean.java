package com.thoughtworks.ketsu.web.beans;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderBaseResponseBean implements Record{
    Order order;

    public OrderBaseResponseBean(Order order) {
        this.order = order;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("uri", routes.orderUrlString(order.getUserId()) + "/" + order.getId());
            put("name", order.getName());
            put("address", order.getAddress());
            put("phone", order.getPhone());
            put("created_at", order.getCreatedAt());
            put("total_price", order.getTotalPrice());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
