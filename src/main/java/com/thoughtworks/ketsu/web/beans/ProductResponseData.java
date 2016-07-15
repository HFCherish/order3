package com.thoughtworks.ketsu.web.beans;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

public class ProductResponseData implements Record{
    Product product;
    UriInfo uriInfo;

    public ProductResponseData(Product product, UriInfo uriInfo) {
        this.product = product;
        this.uriInfo = uriInfo;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("uri", uriInfo.getPath());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
