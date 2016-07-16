package com.thoughtworks.ketsu.domain;

public class OrderItem {
    private String productId;
    private int quantity;
    private double amount;

    public OrderItem(String productId, int quantity, double amount) {
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }

    private OrderItem() {
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public double getAmount() {
        return amount;
    }
}
