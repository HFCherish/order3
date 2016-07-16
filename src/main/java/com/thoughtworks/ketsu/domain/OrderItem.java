package com.thoughtworks.ketsu.domain;

public class OrderItem {
    private String productId;
    private int quantity;

    public OrderItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    private OrderItem() {
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

}
