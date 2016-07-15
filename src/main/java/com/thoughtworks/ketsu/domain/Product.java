package com.thoughtworks.ketsu.domain;

import java.util.UUID;

public class Product {
    private String name;
    private String description;
    private double price;
    private String id;

    public Product(String name, String description, double price) {
        this();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
