package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    OrderRepository orderRepository;

    @Inject
    PaymentRepository paymentRepository;

    private Order order;

    @Before
    public void setUp() {
        order = prepareOrder(prepareUser(userRepository),
                prepareProduct(productRepository),
                orderRepository);
    }

    @Test
    public void should_save_and_get_payment() {
        Payment payment = paymentForTest(order.getId());

        paymentRepository.save(payment);
        Optional<Payment> fetched = paymentRepository.findByOrder(order.getId());

        assertThat(fetched.isPresent(), is(true));
        Payment fetchedPayment = fetched.get();
        assertThat(fetchedPayment.getType(), is(payment.getType()));
    }
}
