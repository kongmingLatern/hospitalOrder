package com.cle.service.Impl;

import com.cle.dao.UserDao;
import com.cle.domain.User;
import com.cle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectAll() {
        List<User> users = userDao.selectAll();
        return users;
    }

    @Override
    public boolean add(User user) {
        return userDao.add(user)>0;
    }


    @Override
    public boolean delete(String uid) {
        return userDao.delete(uid)>0;
    }

    @Override
    public User selectByUid(String uid) {
        User user = userDao.selectByUid(uid);
        return user;
    }

    @Override
    public boolean update(User user) {
        return userDao.change(user)>0;
    }

    @Override
    public User login(User user) {
        String userName = user.getUserName();
        User user1 = userDao.selectUserByUsername(userName);
        return user1;
    }

}
