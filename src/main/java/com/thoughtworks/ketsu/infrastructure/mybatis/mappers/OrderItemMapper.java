package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.OrderItem;
import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
    void save(@Param("orderItem") OrderItem orderItem, @Param("orderId") String orderId);
}
