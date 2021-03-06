package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.infrastructure.repositories.OrderRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.PaymentRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.notNullValue;
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

    @Inject
    PaymentRepository paymentRepository;

    private Order order;
    private String paymentBaseUrl;
    private String orderUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        order = prepareOrder(prepareUser(userRepository),
                prepareProduct(productRepository),
                orderRepository);
        orderUrl = "users/" + order.getUserId() + "/orders/" + order.getId();
        paymentBaseUrl = orderUrl + "/payment";
    }

    @Test
    public void should_pay_successfully() {
        Response response = target(paymentBaseUrl).request().post(Entity.json(paymentJsonForTest()));

        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_get_payment_successfully() {
        Payment payment = preparePayment(order.getId(), paymentRepository);
        Response response = target(paymentBaseUrl).request().get();

        assertThat(response.getStatus(), is(200));
        Map paymentInfo = response.readEntity(Map.class);
        assertThat(paymentInfo.get("pay_type"), is(payment.getType().name()));
        assertThat(paymentInfo.get("amount"), is(payment.getAmount()));
        assertThat(paymentInfo.get("order_uri"), is(orderUrl));
        assertThat(paymentInfo.get("uri"), is(paymentBaseUrl));
        assertThat(paymentInfo.get("created_at"), is(notNullValue()));
    }

    @Test
    public void should_404_when_get_payment_given_not_pay() {
        Response response = target(paymentBaseUrl).request().get();

        assertThat(response.getStatus(), is(404));

    }
}
