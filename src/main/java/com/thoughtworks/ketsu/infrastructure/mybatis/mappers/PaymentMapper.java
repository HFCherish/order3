package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {
    void save(@Param("payment") Payment payment);

    Payment findByOrder(@Param("orderId") String orderId);
}
