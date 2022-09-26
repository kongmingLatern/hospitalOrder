package com.cle.mapper;

import com.cle.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    /**
     * 查询所有预约单
     *
     * @return List<Order>
     */
    @Select("SELECT * from `order` ")
    public List<Order> selectAll();

    /**
     * 添加预约
     *
     * @param order
     * @return int 预约条数
     */
    @Insert("insert into `order` values (#{orderId},#{uid},#{orderTime},#{orderRoom},#{isCancel},#{isFinish})")
    int add(Order order);
}
