package com.cle.service;


import com.cle.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
    boolean add(User user);


    /**
     * 根据uid删除用户
     *
     * @param uid
     * @return
     */
    boolean delete(String uid);

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
    boolean update(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    boolean Ban(String uid);
}
