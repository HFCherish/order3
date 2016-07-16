package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    public static final String VALID_USER_NAME = "Petrina";
    public static final String INVALID_USER_NAME = "JFLDS.I09";
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
        return new Product( "Imran", "teacher", 1000.1);
    }

    public static Product prepareProduct(ProductRepository productRepository) {
        Product product = productForTest();
        productRepository.save(product);
        return product;
    }

    public static Map<String, Object> orderJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "Imran");
            put("address", "beijing");
            put("phone", "568790");
            put("order_items", Arrays.asList(new HashMap(){{
                put("product_id", 1);
                put("quantity", 2);
            }}));
        }};
    }

    public static Order orderForTest(User user) {
        return new Order("Imran", user.getId(),"beijing", "5787", Arrays.asList(new OrderItem()));
    }
}
