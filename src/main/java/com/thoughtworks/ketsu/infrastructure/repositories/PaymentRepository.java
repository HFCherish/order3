package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Payment;

import java.util.Optional;

public interface PaymentRepository {
    void save(Payment payment);

    Optional<Payment> findByOrder(String orderId);
}
