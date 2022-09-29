package com.cle.mapper;

import com.cle.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Insert("insert into `order` values (#{orderId},#{uid},#{orderTime},#{orderRoom},#{isCancel},#{isFinish})")
    int add(Order order);

    /**
     * 删除预约
     *
     * @param uid
     * @return
     */
    @Delete("delete from `order` where rid = #{rid}")
    int delete(@Param("rid") String uid);

    /**
     *
     */
    @Select("select * from `order` where rid=#{rid}")
    List<Order> selectByUid(@Param("rid") String uid);
}
