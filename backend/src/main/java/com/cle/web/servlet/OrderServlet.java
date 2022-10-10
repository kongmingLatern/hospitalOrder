package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cle.pojo.Doctor;
import com.cle.pojo.Message;
import com.cle.pojo.Order;
import com.cle.service.DoctorService;
import com.cle.service.Imlp.DoctorServiceImpl;
import com.cle.service.Imlp.OrderServiceImpl;
import com.cle.service.Imlp.UserServiceImpl;
import com.cle.service.OrderService;
import com.cle.service.UserService;
import com.cle.util.AddNameUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/order/*")
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();
    UserService userService = new UserServiceImpl();
    DoctorService doctorService = new DoctorServiceImpl();

    /**
     * 查询预约单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.selectAll();
        String json = JSON.toJSONString(orders);
        List<Map<String, String>> list = (List<Map<String, String>>) JSONArray.parse(json);
        for (Map<String, String> map : list) {
            AddNameUtil.mapAddRname(map);
            AddNameUtil.mapAddRealName(map);
            AddNameUtil.mapAddDoctorName(map);
        }
        String jsonString = JSON.toJSONString(list);
        resp.getWriter().write(jsonString);
    }

    /**
     * 添加预约
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = new Message();
        boolean flag = true;
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Order order = JSON.parseObject(json, Order.class);
        String uid = order.getUid();
        List<Order> orders = orderService.selectByUid(uid);
        for (Order order1 : orders) {
            if (order1.getIsFinish() == 0 && order1.getIsCancel() == 0 && order1.getOrderTime().getTime() == order.getOrderTime().getTime() && order1.getDoctorId().equals(order.getDoctorId())) {
                resp.setStatus(400);
                message.setMessage("还搁着预约呢？早就预约过了");
                flag = false;
                break;
            }
        }
        Doctor doctor = doctorService.selectByDoctorId(order.getDoctorId());
        int leftCount = doctor.getLeftCount();
        int limitCount = doctor.getLimitCount();
        int orderNo = limitCount - leftCount + 1;
        if (orderNo <= 0) {
            message.setMessage("已经预约满啦");
            resp.setStatus(400);
        }
        order.setOrderNo(orderNo);
        if (flag) {
            int count = orderService.add(order);
            if (count != 0) {
                message.setMessage("预约成功");
            } else {
                message.setMessage("预约失败");
                resp.setStatus(400);
            }
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

    /**
     * 删除预约
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = new Message();
        String uid = req.getParameter("orderId");
        int count = orderService.delete(uid);
        if (count != 0) {
            message.setMessage("删除成功");
        } else {
            message.setMessage("删除失败");
            resp.setStatus(400);
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

    /**
     * 通过uid查询当前用户的预约
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByUid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        List<Order> orders = orderService.selectByUid(uid);
        String json = JSON.toJSONString(orders);
        List<Map<String, String>> list = (List<Map<String, String>>) JSONArray.parse(json);
        for (Map<String, String> map : list) {
            AddNameUtil.mapAddRname(map);
            AddNameUtil.mapAddRealName(map);
            AddNameUtil.mapAddDoctorName(map);
        }
        String jsonString = JSON.toJSONString(list);
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改预约
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void change(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = new Message();
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Order order = JSON.parseObject(json, Order.class);
        int count = orderService.change(order);
        if (count != 0) {
            message.setMessage("修改成功");
        } else {
            message.setMessage("修改失败");
            resp.setStatus(400);
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

    /**
     * 通过orderid查询预约
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        Order order = orderService.selectByOrderId(orderId);
        String json = JSON.toJSONString(order);
        Map map = JSON.parseObject(json, Map.class);
        AddNameUtil.mapAddRname(map);
        AddNameUtil.mapAddRealName(map);
        AddNameUtil.mapAddDoctorName(map);
        String jsonString = JSON.toJSONString(map);
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改是否取消预约状态
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void changeIsCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = new Message();
        String orderId = req.getParameter("orderId");
        String _isCancel = req.getParameter("isCancel");
        int isCancel = Integer.parseInt(_isCancel);
        int count = orderService.changeIsCancel(orderId, isCancel);
        if (count != 0) {
            message.setMessage("取消成功");
            userService.cancel(req);
        } else {
            message.setMessage("取消失败");
            resp.setStatus(400);
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

}
