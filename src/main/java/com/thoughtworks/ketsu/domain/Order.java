package com.thoughtworks.ketsu.domain;

import java.util.List;

public class Order {
    private String id;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;

    public Order(String name, String address, String phone, List<OrderItem> orderItems) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.orderItems = orderItems;
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
}
