package com.cle.service.Imlp;

import com.cle.mapper.DoctorMapper;
import com.cle.pojo.Doctor;
import com.cle.pojo.PageBean;
import com.cle.pojo.User;
import com.cle.service.DoctorService;
import com.cle.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    @Override
    public List<Doctor> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        List<Doctor> list = mapper.selectAll();
        sqlSession.close();
        return list;
    }

    @Override
    public int add(Doctor doctor) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        int count = mapper.add(doctor);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int delete(String doctorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        int count = mapper.delete(doctorId);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public PageBean<Doctor> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<Doctor> doctors = mapper.selectByPage(begin, size);
        int count = mapper.count();
        PageBean<Doctor> pageBean = new PageBean<>();
        pageBean.setRows(doctors);
        pageBean.setTotalCount(count);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public List<Doctor> selectDoctorByRid(String rid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        List<Doctor> doctors = mapper.selectDoctorByRid(rid);
        sqlSession.close();
        return doctors;
    }

    @Override
    public int change(Doctor doctor) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        int count = mapper.change(doctor);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public Doctor selectByDoctorId(String doctorId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        Doctor doctor = mapper.selectDoctorByDoctorId(doctorId);
        sqlSession.close();
        return doctor;
    }

    @Override
    public void flush() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        List<Doctor> doctors = mapper.selectAll();
        for (Doctor doctor : doctors) {
            doctor.setLeftCount(0);
            mapper.change(doctor);
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
