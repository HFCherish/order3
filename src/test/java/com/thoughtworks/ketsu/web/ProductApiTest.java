package com.thoughtworks.ketsu.web;

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
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {

    @Inject
    ProductRepository productRepository;
    private String productsBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        productsBaseUrl = "/products";
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
        assertThat(prodInfo.get("name").toString(), is(product.getName()));
        assertThat((double)prodInfo.get("price"), is(closeTo(product.getPrice(), 0.01)));
        assertThat(prodInfo.get("description").toString(), is(product.getDescription()));
    }

    @Test
    public void should_404_when_get_some_product_given_invalid_id() {
        Product product = prepareProduct(productRepository);
        String getOneUrl = productsBaseUrl + "/" + INVALID_ID;

        Response response = target(getOneUrl).request().get();

        assertThat(response.getStatus(), is(404));

    }

    @Test
    public void should_get_all_successfully() {
        Product product = prepareProduct(productRepository);

        Response response = target(productsBaseUrl).request().get();

        assertThat(response.getStatus(), is(200));
        List products = response.readEntity(List.class);
        assertThat(products.size(), is(1));
        Map fetchedProd = (Map)products.get(0);
//        assertThat(fetchedProd.get("uri"), is(productsBaseUrl + "/" + product.getId()));
        assertThat(fetchedProd.get("uri"), is(notNullValue()));
    }
}
