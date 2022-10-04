package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cle.pojo.Message;
import com.cle.pojo.Order;
import com.cle.pojo.PageBean;
import com.cle.pojo.User;
import com.cle.service.Imlp.OrderServiceImpl;
import com.cle.service.Imlp.UserServiceImpl;
import com.cle.service.OrderService;
import com.cle.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/api/user/*")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();
    OrderService orderService = new OrderServiceImpl();

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
        Date date = new Date();
        User user;
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;
        long diffTimes = 30 * 24 * 60 * 60 * 1000L;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String _remember = req.getParameter("remember");
        boolean remember = Boolean.parseBoolean(_remember);
        user = userService.selectByUsername(username);
        Map map = null;
        Message message = new Message();
        if (user == null) {
            message.setMessage("没有该用户");
            resp.setStatus(400);
        } else if (!password.equals(user.getPassword())) {
            message.setMessage("密码错误");
            resp.setStatus(400);
        } else {
            message.setMessage("登录成功");
            HttpSession session = req.getSession();
            session.setAttribute("uid", user.getUid());
            session.setAttribute("cancelCount", user.getCancelCount());
            session.setAttribute("isAllow", user.getIsAllow());
            List<Order> orders = orderService.selectByUid(user.getUid());
            for (Order order : orders) {
                if (date.getTime() > order.getOrderTime().getTime() && order.getIsCancel() == 0) {
                    orderService.changeIsCancel(order.getOrderId(), 1);
                    userService.cancel(req);
                }
            }
            if ((Integer) session.getAttribute("isAllow") == 1) {
                user = userService.selectByUsername(username);
                long cancelBan = (date.getTime() - user.getBanTime().getTime());
                if (cancelBan >= diffTimes) {
                    userService.changeisAllow(user.getUid(), 0);
                    userService.changeCancelCount(user.getUid(), 0);
                } else {
                    long time = diffTimes - cancelBan;
                    long day = time / dd;
                    long hour = (time - day * dd) / hh;
                    long minute = (time - day * dd - hour * hh) / mi;
                    long second = (time - day * dd - hour * hh - minute * mi) / ss;
                    message.setMessage("该账号已封禁" + " 还剩" + day + "天" + hour + "时" + minute + "分" + second + "秒解封");
                    resp.setStatus(400);
                }
            }
            String jsonString = JSON.toJSONString(user);
            map = JSON.parseObject(jsonString, Map.class);
            if (user.getIsAuth() == 1) {
                map.remove("isAuth");
                map.put("isAuth", true);
            } else {
                map.remove("isAuth");
                map.put("isAuth", false);
            }
            map.put("message", message.getMessage());
            if (remember) {
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                c_username.setMaxAge(7 * 60 * 60 * 24);
                c_password.setMaxAge(7 * 60 * 60 * 24);
                resp.addCookie(c_username);
                resp.addCookie(c_password);
            }
        }
        String jsonString;
        if (map != null) {
            jsonString = JSON.toJSONString(map);
        } else {
            jsonString = JSON.toJSONString(message);
        }
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

    public void change(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = new Message();
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        User user = JSON.parseObject(json, User.class);
        int count = userService.change(user);
        if (count != 0) {
            message.setMessage("修改成功");
        } else {
            message.setMessage("修改失败");
            resp.setStatus(400);
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

    public void selectByUid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        User user = userService.selectByUid(uid);
        String jsonString = JSON.toJSONString(user);
        resp.getWriter().write(jsonString);
    }
}
