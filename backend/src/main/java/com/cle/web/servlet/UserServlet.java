package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cle.pojo.PageBean;
import com.cle.pojo.User;
import com.cle.service.Imlp.UserServiceImpl;
import com.cle.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/user/*")
public class UserServlet extends BaseServlet {
    private class Message {
        private String message;
        private boolean isAuth;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isAuth() {
            return isAuth;
        }

        public void setAuth(boolean auth) {
            isAuth = auth;
        }
    }

    UserService userService = new UserServiceImpl();

    /**
     * 查询所有用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.selectAll();
        String jsonString = JSON.toJSONString(users);
        resp.getWriter().write(jsonString);
    }

    /**
     * 注册
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        User user = JSON.parseObject(json, User.class);
        user.setCancelCount(0);
        user.setIsAllow(0);
        user.setIsAuth(0);
        Message message = new Message();
        int count = userService.add(user);
        if (count != 0) {
            message.setMessage("注册成功");
        } else {
            message.setMessage("注册失败");
            resp.setStatus(400);
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

    /**
     * 登录
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String _remeber = req.getParameter("remeber");
        boolean remeber = Boolean.parseBoolean(_remeber);
        User user = userService.selectByUsername(username);
        Message message = new Message();
        message.setAuth(false);
        if (user == null) {
            message.setMessage("没有该用户");
            resp.setStatus(400);
        } else if (!password.equals(user.getPassword())) {
            message.setMessage("密码错误");
            resp.setStatus(400);
        } else {
            if (user.getIsAuth() == 1) {
                message.setAuth(true);
            }
            message.setMessage("登录成功");
            if (remeber) {
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                c_username.setMaxAge(7 * 60 * 60 * 24);
                c_password.setMaxAge(7 * 60 * 60 * 24);
                resp.addCookie(c_username);
                resp.addCookie(c_password);
            }
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

    /**
     * 分页查询
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<User> pageBean = userService.selectByPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        resp.getWriter().write(jsonString);
    }

    /**
     * 删除用户
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        int delete = userService.delete(uid);
        Message message = new Message();
        if (delete != 0) {
            message.setMessage("删除成功");
        } else {
            message.setMessage("删除失败");
            resp.setStatus(400);
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

    /**
     * 后台的添加用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        User user = JSON.parseObject(json, User.class);
        user.setCancelCount(0);
        user.setIsAllow(0);
        Message message = new Message();
        int count = userService.add(user);
        if (count != 0) {
            message.setMessage("添加成功");
        } else {
            message.setMessage("添加失败");
            resp.setStatus(400);
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }
}
