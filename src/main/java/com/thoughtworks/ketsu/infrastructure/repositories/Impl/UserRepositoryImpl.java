package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;

import javax.inject.Inject;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Inject
    UserMapper mapper;

    @Override
    public void save(com.thoughtworks.ketsu.domain.user.User user) {
        mapper.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(mapper.findById(id));
    }

}
