package com.cle.mapper;

import com.cle.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Select("SELECT * from `order` ")
    public List<Order> selectAll();

    @Insert("insert into `order` values (#{orderId},#{uid},#{orderTime},#{orderRoom},#{isCancel},#{isFinish})")
    int add(Order order);
}
