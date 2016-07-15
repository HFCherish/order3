package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.MainServer;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.prepareProduct;
import static com.thoughtworks.ketsu.support.TestHelper.productForTest;
import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {

    @Inject
    ProductRepository productRepository;
    private String productsBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        productsBaseUrl = "products";
    }

    @Test
    public void should_create_product_successfully() {

        Response response = target(productsBaseUrl).request().post(Entity.json(productJsonForTest()));

        assertThat( response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(productsBaseUrl));

    }

    @Test
    public void should_get_one_product_successfully() {
        Product product = prepareProduct(productRepository);
        String getOneUrl = productsBaseUrl + "/" + product.getId();

        Response response = target(getOneUrl).request().get();

        assertThat(response.getStatus(), is(200));
        Map prodInfo = response.readEntity(Map.class);
        assertThat(prodInfo.get("uri").toString(), is(getOneUrl));
    }
}
