package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {

    @Test
    public void should_create_product_successfully() {
        String productsUrl = "/products";

        Response response = target(productsUrl).request().post(Entity.json(productJsonForTest()));

        assertThat( response.getStatus(), is(201));

    }
}
