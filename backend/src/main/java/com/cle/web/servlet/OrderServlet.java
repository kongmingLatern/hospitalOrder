package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cle.pojo.Order;
import com.cle.service.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/order/*")
public class OrderServlet extends BaseServlet {
    OrderServiceImpl orderService = new OrderServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> list = orderService.selectAll();
        String jsonString = JSON.toJSONString(list);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
