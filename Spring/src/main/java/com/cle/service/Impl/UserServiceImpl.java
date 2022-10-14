package com.cle.service.Impl;

import com.cle.dao.UserDao;
import com.cle.domain.Order;
import com.cle.domain.User;
import com.cle.service.OrderService;
import com.cle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderService orderService;
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
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;
        long diffTimes = 30 * 24 * 60 * 60 * 1000L;
        Date date = new Date();
        String userName = user.getUserName();
        User user1 = userDao.selectUserByUsername(userName);
        List<Order> orders = orderService.selectByUid(user.getUid());
        for (Order order : orders) {
            if (date.getTime() > order.getOrderTime().getTime() && order.getIsCancel() == 0 && order.getIsFinish() == 0) {
                orderService.cancelOrder(order.getOrderId());
            }
        }
        long cancelBan = (date.getTime() - user1.getBanTime().getTime());
        if (cancelBan >= diffTimes) {
            userDao.changeIsAllow(user.getUid(), 0);
            userDao.changeCancelCount(user.getUid(), 0);
        }
        long firstCancel = (date.getTime() - user1.getFirstCancelTime().getTime());
        if (firstCancel >= diffTimes) {
            userDao.changeCancelCount(user.getUid(), 0);
        }
        user1 = userDao.selectUserByUsername(userName);
        return user1;
    }
    @Override
    public boolean Ban(String uid)
    {
        int allow = userDao.changeIsAllow(uid, 1);
        if(allow>0) {
            int ban = userDao.changeBanTime(uid, new Date());
            if(ban>0)
            {
                return true;
            }
        }
        return false;
    }
}
