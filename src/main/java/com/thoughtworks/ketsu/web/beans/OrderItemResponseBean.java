package com.thoughtworks.ketsu.web.beans;

import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class OrderItemResponseBean implements Record {
    private OrderItem orderItem;

    public OrderItemResponseBean(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("product_id", orderItem.getProductId());
            put("quantity", orderItem.getQuantity());
            put("amount", orderItem.getAmount());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
