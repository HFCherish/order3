package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
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
        //given
        Order order = orderForTest(user, product);

        //when
        orderRepository.save(order);
        Optional<Order> fetched = orderRepository.findById(order.getId());

        //then
        assertThat(fetched.isPresent(), is(true));
        Order fetchedOrder = fetched.get();

        verifyOrderIsSame(order, fetchedOrder);

        assertThat(fetchedOrder.getOrderItems().size(), is(1));
        OrderItem orderItem = fetchedOrder.getOrderItems().get(0);
        OrderItem expectedOrderItem = order.getOrderItems().get(0);
        verifyOrderItemIsSame(orderItem, expectedOrderItem);
    }

    private void verifyOrderItemIsSame(OrderItem orderItem, OrderItem expectedOrderItem) {
        assertThat(orderItem.getProductId(), is(expectedOrderItem.getProductId()));
        assertThat(orderItem.getQuantity(), is(expectedOrderItem.getQuantity()));
        assertThat(orderItem.getAmount(), is(expectedOrderItem.getAmount()));
    }

    private void verifyOrderIsSame(Order order, Order fetchedOrder) {
        assertThat(fetchedOrder.getId(), is(order.getId()));
        assertThat(fetchedOrder.getName(), is(order.getName()));
        assertThat(fetchedOrder.getAddress(), is(order.getAddress()));
        assertThat(fetchedOrder.getPhone(), is(order.getPhone()));
        assertThat(fetchedOrder.getTotalPrice(), is(order.getTotalPrice()));
        assertThat(fetchedOrder.getCreatedAt(), is(order.getCreatedAt()));
    }

    @Test
    public void should_get_all_orders() {
        Order order = prepareOrder(user, product, orderRepository);

        List<Order> fetched = orderRepository.findAllOfUser(user.getId());

        assertThat(fetched.size(), is(1));
        verifyOrderIsSame(order, fetched.get(0));
    }
}
