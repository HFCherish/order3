package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.repositories.OrderRepository;
import com.thoughtworks.ketsu.infrastructure.util.OrderService;
import com.thoughtworks.ketsu.web.beans.OrderRequestBean;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Arrays;

public class OrdersApi {
    private User user;

    public OrdersApi(User user) {
        this.user = user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder(OrderRequestBean orderRequestBean,
                               @Context UriInfo uriInfo,
                               @Context OrderRepository orderRepository,
                               @Context OrderService orderService) {
        orderRepository.save(orderService.createOrder(orderRequestBean, user));
        return Response.created(uriInfo.getRequestUri()).build();
    }

    @Path("{orderId}")
    public OrderApi getOneOrder(@PathParam("orderId") String orderId,
                                @Context OrderRepository orderRepository) {
        return orderRepository.findById(orderId)
                .map(OrderApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
