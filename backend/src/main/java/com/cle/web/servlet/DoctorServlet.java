package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cle.pojo.Doctor;
import com.cle.pojo.Message;
import com.cle.pojo.PageBean;
import com.cle.service.DoctorService;
import com.cle.service.Imlp.DoctorServiceImpl;
import com.cle.service.Imlp.RoomServiceImpl;
import com.cle.service.RoomService;
import com.cle.util.AddNameUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/doctor/*")
public class DoctorServlet extends BaseServlet {
    DoctorService doctorService = new DoctorServiceImpl();
    RoomService roomService = new RoomServiceImpl();

    /**
     * 查询所有医生
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Doctor> doctors = doctorService.selectAll();
        String json = JSON.toJSONString(doctors);
        List<Map<String, String>> list = (List<Map<String, String>>) JSONArray.parse(json);
        for (Map<String, String> map : list) {
            AddNameUtil.mapAddRname(map);
        }
        String jsonString = JSON.toJSONString(list);
        resp.getWriter().write(jsonString);
    }

    /**
     * 添加医生
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Doctor doctor = JSON.parseObject(json, Doctor.class);
        int count = doctorService.add(doctor);
        Message message = new Message();
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
     * 删除医生
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("doctorId");
        Message message = new Message();
        int count = doctorService.delete(uid);
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
     * 医生分页查询
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
        PageBean<Doctor> pageBean = doctorService.selectByPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        resp.getWriter().write(jsonString);
    }

    /**
     * 通过科室的rid查询医生
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectDoctorByRid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        List<Doctor> doctors = doctorService.selectDoctorByRid(rid);
        String jsonString = JSON.toJSONString(doctors);
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改医生
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
        Doctor doctor = JSON.parseObject(json, Doctor.class);
        int count = doctorService.change(doctor);
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
