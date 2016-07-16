package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    void save(@Param("order") Order order);

    Order findById(@Param("id") String id);
}
