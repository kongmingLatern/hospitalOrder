package com.cle.service;

import com.cle.domain.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
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
    boolean add(Order order);

    /**
     * 删除预约单
     *
     * @param orderId
     * @return
     */
    boolean delete(String orderId);

    /**
     * 根据uid查询orders
     *
     * @param uid
     * @return
     */
    List<Order> selectByUid(String uid);

    /**
     * 根据orderId查询预约
     *
     * @param orderId
     * @return
     */
    Order selectByOrderId(String orderId);

    /**
     * 修改预约
     *
     * @param order
     * @return
     */
    boolean update(Order order);

    /**
     * 取消预约
     *
     * @param orderId
     * @param
     * @return
     */
    boolean cancelOrder(String orderId);
}
