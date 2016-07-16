package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(String id);
}
