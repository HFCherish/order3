package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.*;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.repositories.OrderRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.PaymentRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    public static final String VALID_USER_NAME = "Petrina";
    public static final String INVALID_USER_NAME = "JFLDS.I09";
    public static final String ORDER_RECEIVER_NAME = "Imran";
    public static String INVALID_ID = "123";

    public static User userForTest() {
        return new User(VALID_USER_NAME);
    }


    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

    public static User prepareUser(UserRepository userRepository) {
        User user = userForTest();
        userRepository.save(user);
        return user;
    }

    public static Map<String, Object> productJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "Imran");
            put("description", "teacher");
            put("price", 1000.1);
        }};
    }

    public static Product productForTest() {
        return new Product("Imran", "teacher", 1000.1);
    }

    public static Product prepareProduct(ProductRepository productRepository) {
        Product product = productForTest();
        productRepository.save(product);
        return product;
    }

    public static Map<String, Object> orderJsonForTest(String prodId) {
        return new HashMap<String, Object>() {{
            put("name", ORDER_RECEIVER_NAME);
            put("address", "beijing");
            put("phone", "568790");
            put("order_items", Arrays.asList(new HashMap() {{
                put("product_id", prodId);
                put("quantity", 2);
            }}));
        }};
    }

    public static Map<String, Object> orderJsonWithoutItemsForTest() {
        return new HashMap<String, Object>() {{
            put("name", ORDER_RECEIVER_NAME);
            put("address", "beijing");
            put("phone", "568790");
            put("order_items", new ArrayList());
        }};
    }

    public static Order orderForTest(User user, Product product) {
        return new Order(ORDER_RECEIVER_NAME, user.getId(), "beijing", "5787", Arrays.asList(new OrderItem(product.getId(), 2, product.getPrice())));
    }

    public static Order prepareOrder(User user, Product product, OrderRepository orderRepository) {
        Order order = orderForTest(user, product);
        orderRepository.save(order);
        return order;
    }

    public static Map<String, Object> paymentJsonForTest() {
        return new HashMap<String, Object>() {{
            put("pay_type", "CASH");
            put("amount", 2000.1);
        }};
    }

    public static Payment paymentForTest(String orderId) {
        return new Payment(orderId, PayType.CASH, 2000.1);
    }

    public static Payment preparePayment(String orderId, PaymentRepository paymentRepository) {
        Payment payment = paymentForTest(orderId);
        paymentRepository.save(payment);
        return payment;
    }
}
