package com.thoughtworks.ketsu.domain;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private String userId;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;
    private DateTime createdAt;

    public Order(String name, String userId, String address, String phone, List<OrderItem> orderItems) {
        this();
        this.name = name;
        this.userId = userId;
        this.address = address;
        this.phone = phone;
        this.orderItems = orderItems;
    }

    private Order() {
        this.id = UUID.randomUUID().toString();
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
