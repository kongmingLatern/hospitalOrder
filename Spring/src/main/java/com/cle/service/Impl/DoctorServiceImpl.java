package com.cle.service.Impl;

import com.cle.dao.DoctorDao;
import com.cle.dao.RoomDao;
import com.cle.domain.Doctor;
import com.cle.domain.Order;
import com.cle.domain.Room;
import com.cle.domain.User;
import com.cle.service.DoctorService;
import com.cle.utils.ObjectToMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private RoomDao roomDao;
    @Override
    public List selectAll() {
        List<Doctor> doctors = doctorDao.selectAll();
        List<Map<String, Object>> maps=new ArrayList<>();
        for(Doctor doctor:doctors)
        {
            try {
                Map<String, Object> map = ObjectToMapUtils.convertObjToMap(doctor);
                if(map!=null)
                {
                    Object rid = map.get("rid");
                    if(rid!=null) {
                        Room room = roomDao.selectByRid((String) rid);
                        if(room!=null)
                        {
                            map.put("rname",room.getRname());
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
    public boolean add(Doctor doctor) {
        return doctorDao.add(doctor)>0;
    }

    @Override
    public boolean delete(String doctorId) {
       return doctorDao.delete(doctorId)>0;
    }

    @Override
    public List selectDoctorByRid(String rid) {
        List<Doctor> doctors = doctorDao.selectDoctorByRid(rid);
        List<Map<String, Object>> maps=new ArrayList<>();
        for(Doctor doctor:doctors)
        {
            try {
                Map<String, Object> map = ObjectToMapUtils.convertObjToMap(doctor);
                if(map!=null)
                {
                        Room room = roomDao.selectByRid((String) rid);
                        if(room!=null)
                        {
                            map.put("rname",room.getRname());
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
    public boolean update(Doctor doctor) {
        return doctorDao.change(doctor)>0;
    }

    @Override
    public Map selectByDoctorId(String doctorId) {
        Doctor doctor = doctorDao.selectDoctorByDoctorId(doctorId);
        Map<String, Object> map=new HashMap<>();
        try {
        map = ObjectToMapUtils.convertObjToMap(doctor);
            if (map != null) {
                if (doctor != null) {
                    String rid = doctor.getRid();
                    if (rid != null){
                        Room room = roomDao.selectByRid(rid);
                    if (room != null) {
                        map.put("rname", room.getRname());
                    }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void flush() {
    }
}
