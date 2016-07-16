package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.PayType;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.PaymentMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.PaymentRepository;

import javax.inject.Inject;
import java.util.Optional;

public class PaymenRepositoryImpl implements PaymentRepository {
    @Inject
    PaymentMapper paymentMapper;

    @Override
    public void save(Payment payment) {
        paymentMapper.save(payment);
    }

    @Override
    public Optional<Payment> findByOrder(String orderId) {
        return Optional.ofNullable(paymentMapper.findByOrder(orderId));
    }
}
