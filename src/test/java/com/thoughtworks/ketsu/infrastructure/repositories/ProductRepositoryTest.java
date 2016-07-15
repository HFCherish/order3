package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.productForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

@RunWith(ApiTestRunner.class)
public class ProductRepositoryTest {

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_save_and_get_product() {
        Product product = productForTest();

        productRepository.save(product);
        Optional<Product> fetched = productRepository.findById(product.getId());

        assertThat(fetched.isPresent(), is(true));
        Product fetchedProduct = fetched.get();
        assertThat(fetchedProduct.getId(), is(product.getId()));
//        assertThat(fetchedProduct.getName(), is(product.getName()));
//        assertThat(fetchedProduct.getDescription(), is(product.getDescription()));
//        assertThat(fetchedProduct.getPrice(), is(closeTo(product.getPrice(), 0.01)));

    }
}
