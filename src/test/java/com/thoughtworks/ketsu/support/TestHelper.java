package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    public static final String VALID_USER_NAME = "Petrina";
    public static final String INVALID_USER_NAME = "JFLDS.I09";
    private static int auto_increment_key = 1;
    public static String INVALID_ID = "123";
    public static Map<String, Object> deployment(String appName, String releaseId) {
        return new HashMap<String, Object>() {{
            put("app", String.format("http://service-api.tw.com/apps/%s", appName));
            put("release", String.format("http://service-api.tw.com/apps/%s/releases/%s", appName, releaseId));
        }};
    }

    public static Map<String, Object> stackMap(String id, String name) {
        Map<String, Object> stackMap = new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
        }};
        return stackMap;
    }

    public static Map<String, Object> userMap(String email, String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("email", email);
        }};
    }

    public static User userForTest() {
        return new User(VALID_USER_NAME);
    }

//    public static User userFixture(UserRepository userRepository, UserRole role) {
//        final String id = new Integer(auto_increment_key++).toString();
//        User user = userForTest(id, "name-" + id, role);
//        userRepository.save(user);
//        return user;
//    }

    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
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
}
