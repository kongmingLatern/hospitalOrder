package com.cle.service.Impl;

import com.cle.dao.OrderDao;
import com.cle.domain.Order;
import com.cle.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> selectAll() {
        List<Order> orders = orderDao.selectAll();
        return orders;
    }

    @Override
    public boolean add(Order order) {
        return orderDao.add(order)>0;
    }

    @Override
    public boolean delete(String orderId) {
        return orderDao.delete(orderId)>0;
    }

    @Override
    public List<Order> selectByUid(String uid) {
        List<Order> orders = orderDao.selectByUid(uid);
        return orders;
    }

    @Override
    public Order selectByOrderId(String orderId) {
        Order order = orderDao.selectByOrderId(orderId);
        return order;
    }

    @Override
    public boolean update(Order order) {
        return orderDao.change(order)>0;
    }

    @Override
    public boolean cancelOrder(String orderId) {
        return orderDao.changeCancel(orderId,1)>0;
    }
}
