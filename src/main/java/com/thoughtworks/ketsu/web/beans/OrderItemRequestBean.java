package com.thoughtworks.ketsu.web.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderItemRequestBean {
    @JsonProperty("product_id")
    String productId;

    @JsonProperty("quantity")
    int quantity;

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
