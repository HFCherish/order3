package com.thoughtworks.ketsu.web.beans;

import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

public class PaymentResponseBean implements Record {
    private Payment payment;
    private UriInfo payPath;

    public PaymentResponseBean(Payment payment, UriInfo payPath) {
        this.payment = payment;
        this.payPath = payPath;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        String relativeUrl = payPath.getPath();
        return new HashMap(){{
            put("order_uri", relativeUrl.substring(0, relativeUrl.length()-"/payment".length()));
            put("uri", relativeUrl);
            put("pay_type", payment.getType());
            put("amount", payment.getAmount());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
