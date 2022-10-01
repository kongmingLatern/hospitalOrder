package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cle.pojo.Message;
import com.cle.pojo.Order;
import com.cle.service.Imlp.OrderServiceImpl;
import com.cle.service.OrderService;
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
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Order order = JSON.parseObject(json, Order.class);
        int count = orderService.add(order);
        if (count != 0) {
            message.setMessage("添加成功");
        } else {
            message.setMessage("添加失败");
            resp.setStatus(400);
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
}
