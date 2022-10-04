package com.cle.service;

import com.cle.pojo.PageBean;
import com.cle.pojo.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * @param currentPage 当前页码
     * @param pageSize    每页展示条数
     * @return
     */
    PageBean<User> selectByPage(int currentPage, int pageSize);

    /**
     * 根据uid删除用户
     *
     * @param uid
     * @return
     */
    int delete(String uid);

    /**
     * 根据uid查询用户
     *
     * @param uid
     * @return
     */
    User selectByUid(String uid);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int change(User user);

    /**
     * 修改cancelCount次数
     *
     * @param uid
     * @param cancelCount
     * @return
     */
    int changeCancelCount(String uid, int cancelCount);

    /**
     * 操作cancelCount次数
     *
     * @param req
     */
    void cancel(HttpServletRequest req);

    /**
     * 管理是否封禁
     */
    int changeisAllow(String uid, int isAllow);

    /**
     * 修改第一次取消时间
     *
     * @param uid
     * @param firstCancelTime
     * @return
     */
    int changeFirstCancelTime(String uid, Date firstCancelTime);

    /**
     * 修改封禁时间
     *
     * @param uid
     * @param banTime
     * @return
     */
    int changeBanTime(String uid, Date banTime);
}
