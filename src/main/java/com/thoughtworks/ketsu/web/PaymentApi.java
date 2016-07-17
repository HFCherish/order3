package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.PayType;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.infrastructure.repositories.PaymentRepository;
import com.thoughtworks.ketsu.web.beans.PaymentResponseBean;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

public class PaymentApi {
    private Order order;

    public PaymentApi(Order order) {
        this.order = order;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(Map paymentInfo,
                        @Context UriInfo uriInfo,
                        @Context PaymentRepository paymentRepository) {
        paymentRepository.save(new Payment(order.getId(), PayType.valueOf(paymentInfo.get("pay_type").toString()),
                (double)paymentInfo.get("amount")));
        return Response.created(uriInfo.getRequestUri()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaymentResponseBean getPayment(@Context PaymentRepository paymentRepository,
                                          @Context UriInfo uriInfo) {
        return new PaymentResponseBean(paymentRepository.findByOrder(order.getId())
                .map(payment -> payment)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND)), uriInfo);
    }
}
