package com.thoughtworks.ketsu.infrastructure.repositories.Impl;

import com.thoughtworks.ketsu.domain.PayType;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.infrastructure.repositories.PaymentRepository;

import java.util.Optional;

public class PaymenRepositoryImpl implements PaymentRepository {
    @Override
    public void save(Payment payment) {

    }

    @Override
    public Optional<Payment> findByOrder(String orderId) {
        return Optional.ofNullable(new Payment("687", PayType.CASH, 23));
    }
}
