package com.cle.service;

import com.cle.pojo.PageBean;
import com.cle.pojo.User;

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
}
