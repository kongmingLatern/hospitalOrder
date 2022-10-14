package com.cle.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.cle.controller.Code;
import com.cle.dao.DoctorDao;
import com.cle.dao.OrderDao;
import com.cle.dao.RoomDao;
import com.cle.dao.UserDao;
import com.cle.domain.Doctor;
import com.cle.domain.Order;
import com.cle.domain.Room;
import com.cle.domain.User;
import com.cle.exception.BusinessException;
import com.cle.service.OrderService;
import com.cle.service.UserService;
import com.cle.utils.ObjectToMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    @Lazy
    private UserService userService;
    @Override
    public List selectAll() {
        List<Order> orders = orderDao.selectAll();
        List<Map<String, Object>> maps=new ArrayList<>();
        for(Order order:orders)
        {
            try {
                Map<String, Object> map = ObjectToMapUtils.convertObjToMap(order);
                if(map!=null)
                {
                    Object uid = map.get("uid");
                    if(uid!=null) {
                        User user = userDao.selectByUid((String) uid);
                        if(user!=null)
                        {
                            map.put("realName",user.getRealName());
                        }
                    }
                    Object rid = map.get("rid");
                    if(rid!=null) {
                        Room room = roomDao.selectByRid((String) rid);
                        if(room!=null)
                        {
                            map.put("rname",room.getRname());
                        }
                    }
                    Object doctorId = map.get("doctorId");
                    if(doctorId!=null) {
                        Doctor doctor = doctorDao.selectDoctorByDoctorId((String) doctorId);
                        if(doctor!=null)
                        {
                            map.put("doctorName",doctor.getDoctorName());
                        }
                    }
                }
                maps.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return maps;
    }

    @Override
    public boolean add(Order order) {
        Date date = new Date();
        String uid = order.getUid();
        List<Order> orders = orderDao.selectByUid(uid);
        order.setOrderTime(date);
        for(Order order1:orders)
        {
            if(order1.getIsFinish() == 0 && order1.getIsCancel() == 0 && order1.getOrderTime().getTime() == order.getOrderTime().getTime() && order1.getDoctorId().equals(order.getDoctorId()))
            {
                throw new BusinessException(Code.SAVE_ERR,"还搁这预约呢？");
            }
        }
        return orderDao.add(order) > 0;
    }

    @Override
    public boolean delete(String orderId) {
        return orderDao.delete(orderId) > 0;
    }

    @Override
    public List selectByUid(String uid) {
        List<Order> orders = orderDao.selectByUid(uid);
        List<Map<String, Object>> maps=new ArrayList<>();
        for(Order order:orders)
        {
            try {
                Map<String, Object> map = ObjectToMapUtils.convertObjToMap(order);
                if(map!=null)
                {
                    if(uid!=null) {
                        User user = userDao.selectByUid((String) uid);
                        if(user!=null)
                        {
                            map.put("realName",user.getRealName());
                        }
                    }
                    Object rid = map.get("rid");
                    if(rid!=null) {
                        Room room = roomDao.selectByRid((String) rid);
                        if(room!=null)
                        {
                            map.put("rname",room.getRname());
                        }
                    }
                    Object doctorId = map.get("doctorId");
                    if(doctorId!=null) {
                        Doctor doctor = doctorDao.selectDoctorByDoctorId((String) doctorId);
                        if(doctor!=null)
                        {
                            map.put("doctorName",doctor.getDoctorName());
                        }
                    }
                }
                maps.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return maps;
    }

    @Override
    public Map selectByOrderId(String orderId) {
        Order order = orderDao.selectByOrderId(orderId);
        Map<String,Object> map = new HashMap<>();
                try {
                     map = ObjectToMapUtils.convertObjToMap(order);
                if (map != null) {
                    Object uid = map.get("uid");
                    if (uid != null) {
                        User user = userDao.selectByUid((String) uid);
                        if (user != null) {
                            map.put("realName", user.getRealName());
                        }
                    }
                    Object rid = map.get("rid");
                    if (rid != null) {
                        Room room = roomDao.selectByRid((String) rid);
                        if (room != null) {
                            map.put("rname", room.getRname());
                        }
                    }
                    Object doctorId = map.get("doctorId");
                    if (doctorId != null) {
                        Doctor doctor = doctorDao.selectDoctorByDoctorId((String) doctorId);
                        if (doctor != null) {
                            map.put("doctorName", doctor.getDoctorName());
                        }
                    }
                }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        return map;
    }

    @Override
    public boolean update(Order order) {
        return orderDao.change(order) > 0;
    }

    @Override
    public boolean cancelOrder(String orderId) {
        Order order = orderDao.selectByOrderId(orderId);
        String uid = order.getUid();
        User user = userDao.selectByUid(uid);
        if(user.getCancelCount()==0)
        {
            userDao.changeFirstCancelTime(uid,new Date());
        }
        userDao.changeCancelCount(uid,user.getCancelCount()+1);
        if((user.getCancelCount()+1)>=3) userService.Ban(uid);
        return orderDao.changeCancel(orderId, 1) > 0;
    }
}
