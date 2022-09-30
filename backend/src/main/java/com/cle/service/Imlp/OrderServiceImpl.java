package com.cle.service.Imlp;

import com.cle.mapper.OrderMapper;
import com.cle.pojo.Order;
import com.cle.service.OrderService;
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
    public int add(Order order) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        int count = mapper.add(order);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int delete(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        int count = mapper.delete(uid);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public List<Order> selectByUid(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orders = mapper.selectByUid(uid);
        sqlSession.close();
        return orders;
    }

    @Override
    public Order selectByOrderId(String orderId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = mapper.selectByOrderId(orderId);
        sqlSession.close();
        return order;
    }
}
