package com.cle.service;

import com.cle.pojo.Order;

import java.util.List;

public interface OrderService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Order> selectAll();

    /**
     * 添加预约单
     *
     * @return
     */
    int add(Order order);

    /**
     * 删除预约单
     *
     * @param uid
     * @return
     */
    int delete(String uid);

    /**
     * 根据uid查询orders
     *
     * @param uid
     * @return
     */
    List<Order> selectByUid(String uid);
}
