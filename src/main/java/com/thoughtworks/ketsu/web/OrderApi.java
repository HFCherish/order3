package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.web.beans.OrderResponseBean;
import org.apache.ibatis.annotations.Param;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class OrderApi {
    private Order order;

    public OrderApi(Order order) {
        this.order = order;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OrderResponseBean getOrder() {
        return new OrderResponseBean(order);
    }

    @Path("payment")
    public PaymentApi toPaymentApi() {
        return new PaymentApi(order);
    }
}
