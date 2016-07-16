package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.orderForTest;
import static com.thoughtworks.ketsu.support.TestHelper.prepareProduct;
import static com.thoughtworks.ketsu.support.TestHelper.prepareUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrderRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    OrderRepository orderRepository;

    private User user;
    private Product product;

    @Before
    public void setUp() {
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);

    }

    @Test
    public void should_save_and_get_an_order() {
        Order order = orderForTest(user);

        orderRepository.save(order);
        Optional<Order> fetched = orderRepository.findById(order.getId());

        assertThat(fetched.isPresent(), is(true));
        Order fetchedOrder = fetched.get();
        assertThat(fetchedOrder.getId(), is(order.getId()));
        assertThat(fetchedOrder.getName(), is(order.getName()));
        assertThat(fetchedOrder.getAddress(), is(order.getAddress()));
        assertThat(fetchedOrder.getPhone(), is(order.getPhone()));

    }
}
