package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static com.thoughtworks.ketsu.support.TestHelper.userJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserApiTest extends ApiSupport {

    private String usersBaserUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        usersBaserUrl = "/users";
    }

    @Test
    public void should_register_successfully() {
        Response response = target(usersBaserUrl).request().post(Entity.json(userJsonForTest()));

        assertThat(response.getStatus(), is(201));
    }
}
