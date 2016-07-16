package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.repositories.OrderRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    OrderRepository orderRepository;

    private Order order;
    private String paymentBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        order = prepareOrder(prepareUser(userRepository),
                prepareProduct(productRepository),
                orderRepository);
        paymentBaseUrl = "/users/" + order.getUserId() + "/orders/" + order.getId() + "/payment";
    }

    @Test
    public void should_pay_successfully() {
        Response response = target(paymentBaseUrl).request().post(Entity.json(paymentJsonForTest()));

        assertThat(response.getStatus(), is(201));
    }
}
