package com.cle.service;

import com.cle.mapper.DoctorMapper;
import com.cle.pojo.Doctor;
import com.cle.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DoctorServiceImpl implements DoctorService{
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public List<Doctor> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        List<Doctor> list = mapper.selectAll();
        return list;
    }

    @Override
    public void add(Doctor doctor) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        mapper.add(doctor);
        sqlSession.commit();
        sqlSession.close();
    }
}
