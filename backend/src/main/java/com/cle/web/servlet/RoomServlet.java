package com.cle.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cle.pojo.Message;
import com.cle.pojo.Room;
import com.cle.service.RoomServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RoomServlet extends BaseServlet {
    RoomServiceImpl roomService = new RoomServiceImpl();

    /**
     * 查询所有
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> rooms = roomService.selectAll();
        String jsonString = JSON.toJSONString(rooms);
        resp.getWriter().write(jsonString);
    }

    /**
     * 添加科室
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
        Room room = JSON.parseObject(json, Room.class);
        int count = roomService.add(room);
        if (count != 0) {
            message.setMessage("添加成功");
        } else {
            message.setMessage("添加失败");
            resp.setStatus(400);
        }
    }

    /**
     * 删除科室
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = new Message();
        String uid = req.getParameter("uid");
        int count = roomService.delete(uid);
        if (count != 0) {
            message.setMessage("删除成功");
        } else {
            message.setMessage("删除失败");
            resp.setStatus(400);
        }
    }

}
