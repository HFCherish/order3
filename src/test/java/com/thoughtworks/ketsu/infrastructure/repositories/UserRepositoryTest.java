package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.userForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_save_and_get_user() {
        User user = userForTest();

        userRepository.save(user);
        Optional<User> fetched = userRepository.findById(user.getId());

        assertThat(fetched.isPresent(), is(true));

    }
}
