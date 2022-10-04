package com.cle.service.Imlp;

import com.cle.mapper.UserMapper;
import com.cle.pojo.PageBean;
import com.cle.pojo.User;
import com.cle.service.UserService;
import com.cle.util.SqlSessionFactoryUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    @Override
    public List<User> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        sqlSession.close();
        return users;
    }

    @Override
    public int add(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.add(user);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public User selectByUsername(String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUserByUsername(username);
        sqlSession.close();
        return user;
    }

    @Override
    public PageBean<User> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<User> users = mapper.selectByPage(begin, size);
        int count = mapper.count();
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(users);
        pageBean.setTotalCount(count);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public int delete(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int delete = mapper.delete(uid);
        sqlSession.commit();
        sqlSession.close();
        return delete;
    }

    @Override
    public User selectByUid(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByUid(uid);
        sqlSession.close();
        return user;
    }

    @Override
    public int change(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.change(user);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int changeCancelCount(String uid, int cancelCount) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.changeCancelCount(uid, cancelCount);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int changeisAllow(String uid, int isAllow) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.changeIsAllow(uid, isAllow);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int changeFirstCancelTime(String uid, Date firstCancelTime) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.changeFirstCancelTime(uid, firstCancelTime);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int changeBanTime(String uid, Date banTime) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.changeBanTime(uid, banTime);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public void cancel(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String uid = (String) session.getAttribute("uid");
        Integer cancelCount = (Integer) session.getAttribute("cancelCount");
        ;
        cancelCount++;
        session.setAttribute("cancelCount", cancelCount);
        if (cancelCount == 1) {
            Date firstCancelTime = new Date();
            changeFirstCancelTime(uid, firstCancelTime);
        }
        if (cancelCount >= 3) {
            Date banTime = new Date();
            changeBanTime(uid, banTime);
            changeisAllow(uid, 1);
            session.setAttribute("isAllow", 1);
        }
        changeCancelCount(uid, cancelCount);
    }

}
