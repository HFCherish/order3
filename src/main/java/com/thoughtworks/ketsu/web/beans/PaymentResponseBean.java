package com.thoughtworks.ketsu.web.beans;

import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class PaymentResponseBean implements Record {
    private Payment payment;

    public PaymentResponseBean(Payment payment) {
        this.payment = payment;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap(){{
            put("pay_type", payment.getType());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
