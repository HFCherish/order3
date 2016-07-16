package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static com.thoughtworks.ketsu.support.TestHelper.INVALID_USER_NAME;
import static com.thoughtworks.ketsu.support.TestHelper.VALID_USER_NAME;
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
        Response response = target(usersBaserUrl).request().post(Entity.json(userJsonForTest(VALID_USER_NAME)));

        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_400_when_register_given_invalid_name() {
        Response response = target(usersBaserUrl).request().post(Entity.json(userJsonForTest(INVALID_USER_NAME)));

        assertThat(response.getStatus(), is(400));

    }
}
