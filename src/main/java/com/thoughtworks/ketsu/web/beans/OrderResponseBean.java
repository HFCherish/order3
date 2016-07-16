package com.thoughtworks.ketsu.web.beans;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.core.UriInfo;
import java.util.*;
import java.util.stream.Collectors;

public class OrderResponseBean implements Record{
    private UriInfo orderPath;
    private Order order;

    public OrderResponseBean(Order order, UriInfo orderPath) {
        this.order = order;
        this.orderPath = orderPath;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        List orderItemsInfo = new ArrayList();
        for(OrderItem orderItem: order.getOrderItems()) {
            orderItemsInfo.add(new OrderItemResponseBean(orderItem).toJson(routes));
        }

        return new HashMap() {{
            put("uri", routes.getRelativeBasePath() + orderPath.getPath());
            put("name", order.getName());
            put("address", order.getAddress());
            put("phone", order.getPhone());
            put("created_at", order.getCreatedAt());
            put("total_price", order.getTotalPrice());
            put("order_items", orderItemsInfo);
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
