package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cle.pojo.Doctor;
import com.cle.service.DoctorServiceImpl;
import com.cle.util.UuidUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
@WebServlet("/doctor/*")
public class DoctorServlet extends BaseServlet{
    DoctorServiceImpl doctorService = new DoctorServiceImpl();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Doctor> list = doctorService.selectAll();
        String jsonString = JSON.toJSONString(list);
       resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Doctor doctor = JSON.parseObject(json, Doctor.class);
        String uuid = UuidUtils.generateShortUuid();
        doctor.setDoctorId(uuid);
        System.out.println(uuid);
        doctorService.add(doctor);
    }
}
