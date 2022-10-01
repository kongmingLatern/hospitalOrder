package com.cle.mapper;

import com.cle.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMapper {
    /**
     * 查询所有预约单
     *
     * @return List<Order>
     */
    @Select("SELECT * from `order` ")
    List<Order> selectAll();

    /**
     * 添加预约
     *
     * @param order
     * @return int 预约条数
     */
    @Insert("insert into `order` values (#{orderId},#{uid},#{orderTime},#{isFinish},#{isCancel},#{rid},#{doctorId})")
    int add(Order order);

    /**
     * 删除预约
     *
     * @param orderId
     * @return
     */
    @Delete("delete from `order` where orderId = #{orderId}")
    int delete(String orderId);

    /**
     * 根据uid查询订单
     *
     * @param uid
     * @return
     */
    @Select("select * from `order` where uid=#{uid}")
    List<Order> selectByUid(String uid);

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    int change(Order order);

    /**
     * 根据orderid查询预约
     *
     * @param orderId
     * @return
     */
    @Select("SELECT * FROM `ORDER` WHERE ORDERID=#{orderId}")
    Order selectByOrderId(String orderId);
}
