package com.cle.service;

import com.cle.mapper.OrderMapper;
import com.cle.pojo.Order;
import com.cle.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    @Override
    public List<Order> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orders = mapper.selectAll();
        sqlSession.close();
        return orders;
    }

    @Override
    public void add() {

    }
}
