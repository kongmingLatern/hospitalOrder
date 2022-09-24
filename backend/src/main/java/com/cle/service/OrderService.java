package com.cle.service;

import com.cle.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> selectAll();

    void add();
}
