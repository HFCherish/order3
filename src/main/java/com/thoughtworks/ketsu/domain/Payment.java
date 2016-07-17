package com.thoughtworks.ketsu.domain;

import org.joda.time.DateTime;

public class Payment {
    private String orderId;
    private PayType type;
    private double amount;
    private DateTime payAt;

    public Payment(String orderId, PayType type, double amount) {
        this.orderId = orderId;
        this.type = type;
        this.amount = amount;
    }

    private Payment() {
    }

    public String getOrderId() {
        return orderId;
    }

    public PayType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public DateTime getPayAt() {
        return payAt;
    }
}
