package com.cle.service;

import com.cle.mapper.RoomMapper;
import com.cle.pojo.Room;
import com.cle.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    /**
     * 查询所有科室
     *
     * @return
     */
    @Override
    public List<Room> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);
        List<Room> rooms = mapper.selectAll();
        sqlSession.close();
        return rooms;
    }

    @Override
    public int add(Room room) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);
        int count = mapper.add(room);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int delete(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoomMapper mapper = sqlSession.getMapper(RoomMapper.class);
        int count = mapper.delete(uid);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }
}
