package com.cle.web.servlet;
import com.alibaba.fastjson.JSON;
import com.cle.pojo.Doctor;
import com.cle.pojo.Message;
import com.cle.pojo.PageBean;
import com.cle.pojo.User;
import com.cle.service.DoctorServiceImpl;
import com.cle.util.UidUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/doctor/*")
public class DoctorServlet extends BaseServlet{
    DoctorServiceImpl doctorService = new DoctorServiceImpl();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Doctor> list = doctorService.selectAll();
        String jsonString = JSON.toJSONString(list);
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Doctor doctor = JSON.parseObject(json, Doctor.class);
        doctor.setDoctorId(UidUtil.getUUID());
        int count = doctorService.add(doctor);
        Message message = new Message();
        if (count != 0) {
            message.setMessage("添加失败");
            resp.setStatus(400);
        } else {
            message.setMessage("添加成功");
        }
        String jsonString = JSON.toJSONString(message);
        resp.getWriter().write(jsonString);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");

    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Doctor> pageBean = doctorService.selectByPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        resp.getWriter().write(jsonString);
    }
}
