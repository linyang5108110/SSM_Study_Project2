package com.linyang.service;

import com.linyang.domian.Order;

import java.util.List;

public interface OrderService {
    List<Order> finall();

    void save(Order order);

    void delOne(Integer id);

    void delMany(Integer[] ids);
}
