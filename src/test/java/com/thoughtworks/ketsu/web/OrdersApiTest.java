package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.repositories.OrderRepository;
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

import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrdersApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    OrderRepository orderRepository;

    private String ordersBaseUrl;
    private User user;
    private Product product;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
        ordersBaseUrl = "/users/" + user.getId() + "/orders";
    }

    @Test
    public void should_build_order_successfully() {
        Response response = target(ordersBaseUrl).request().post(Entity.json(orderJsonForTest(product.getId())));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(ordersBaseUrl));
    }

    @Test
    public void should_400_when_build_order_given_no_items() {
        Response response = target(ordersBaseUrl).request().post(Entity.json(orderJsonWithoutItemsForTest()));

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_400_when_build_order_given_invalid_item() {
        Response response = target(ordersBaseUrl).request().post(Entity.json(orderJsonForTest(INVALID_ID)));

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_get_some_order_of_some_user_successfuly() {
        Order order = prepareOrder(user, product, orderRepository);
        String getOneUrl = ordersBaseUrl + "/" + order.getId();

        Response response = target(getOneUrl).request().get();

        assertThat(response.getStatus(), is(200));
        Map orderInfo = response.readEntity(Map.class);
        assertThat(orderInfo.get("uri"), is(getOneUrl));
        assertThat(orderInfo.get("name"), is(order.getName()));
        assertThat(orderInfo.get("address"), is(order.getAddress()));
        assertThat(orderInfo.get("phone"), is(order.getPhone()));
        assertThat(orderInfo.get("total_price"), is(order.getTotalPrice()));
        assertThat(orderInfo.get("created_at"), is(order.getCreatedAt()));
        List orderItemsInfo = (List) orderInfo.get("order_items");
        assertThat(orderItemsInfo.size(), is(1));
        Map orderItemInfo = (Map)orderItemsInfo.get(0);
        OrderItem expectedItem = order.getOrderItems().get(0);
        assertThat(orderItemInfo.get("product_id"), is(expectedItem.getProductId()));
        assertThat(orderItemInfo.get("quantity"), is(expectedItem.getQuantity()));
        assertThat(orderItemInfo.get("amount"), is(expectedItem.getAmount()));
    }

    @Test
    public void should_400_when_get_some_order_of_some_user_given_invalid_id() {
        Order order = prepareOrder(user, product, orderRepository);
        String getOneUrl = ordersBaseUrl + "/" + INVALID_ID;

        Response response = target(getOneUrl).request().get();

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_200_when_get_all_orders() {
        Order order = prepareOrder(user, product, orderRepository);

        Response response = target(ordersBaseUrl).request().get();

        assertThat(response.getStatus(), is(200));
        List ordersInfo = response.readEntity(List.class);
        assertThat(ordersInfo.size(), is(1));

    }
}
