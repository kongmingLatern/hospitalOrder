package com.cle.util;

import com.cle.pojo.Doctor;
import com.cle.pojo.Room;
import com.cle.pojo.User;
import com.cle.service.DoctorService;
import com.cle.service.Imlp.DoctorServiceImpl;
import com.cle.service.Imlp.OrderServiceImpl;
import com.cle.service.Imlp.RoomServiceImpl;
import com.cle.service.Imlp.UserServiceImpl;
import com.cle.service.OrderService;
import com.cle.service.RoomService;
import com.cle.service.UserService;

import java.util.Map;

public class AddNameUtil {
    static OrderService orderService = new OrderServiceImpl();
    static UserService userService = new UserServiceImpl();
    static RoomService roomService = new RoomServiceImpl();
    static DoctorService doctorService = new DoctorServiceImpl();

    private AddNameUtil() {
    }

    public static void mapAddRname(Map<String, String> map) {
        String rid = map.get("rid");
        if (rid != null) {
            Room room = roomService.selectByRid(rid);
            if (room != null) {
                String rname = room.getRname();
                map.put("rname", rname);
            }
        }
    }

    public static void mapAddRealName(Map<String, String> map) {
        String uid = map.get("uid");
        if (uid != null) {
            User user = userService.selectByUid(uid);
            if (user != null) {
                String realName = user.getRealName();
                map.put("realName", realName);
            }
        }
    }

    public static void mapAddDoctorName(Map<String, String> map) {
        String doctorId = map.get("doctorId");
        if (doctorId != null) {
            Doctor doctor = doctorService.selectByDoctorId(doctorId);
            if (doctor != null) {
                String doctorName = doctor.getDoctorName();
                map.put("doctorName", doctorName);
            }
        }
    }
}
