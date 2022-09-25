package com.cle.service;

import com.cle.pojo.PageBean;
import com.cle.pojo.User;

import java.util.List;

public interface UserService {
    List<User> selectAll();

    int add(User user);

    User selectByUsername(String username);

    /**
     * @param currentPage 当前页码
     * @param pageSize    每页展示条数
     * @return
     */
    PageBean<User> selectByPage(int currentPage, int pageSize);

    /**
     * 删除用户
     */
    int delete(String uid);
}
