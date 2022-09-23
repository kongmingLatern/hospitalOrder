package com.cle.service;

import com.cle.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> selectAll();
    public void add(User user);
}
