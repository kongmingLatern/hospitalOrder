package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cle.pojo.User;
import com.cle.service.UserServiceImpl;
import com.cle.util.UidUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/user/*")
public class UserServlet extends BaseServlet {

    UserServiceImpl userService = new UserServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.selectAll();
        String jsonString = JSON.toJSONString(users);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        User user = JSON.parseObject(json, User.class);
        user.setUid(UidUtil.getUUID());
        user.setCancelCount(0);
        user.setIsAllow(0);
        userService.add(user);
    }

    public void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User user = userService.selectByUsername(username);
        String jsonString = JSON.toJSONString(user);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonString);
    }
}
