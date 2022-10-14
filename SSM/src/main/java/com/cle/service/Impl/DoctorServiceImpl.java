package com.cle.service.Impl;

import com.cle.dao.DoctorDao;
import com.cle.domain.Doctor;
import com.cle.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorDao doctorDao;
    @Override
    public List<Doctor> selectAll() {
        List<Doctor> doctors = doctorDao.selectAll();
        return doctors;
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
    public List<Doctor> selectDoctorByRid(String rid) {
        List<Doctor> doctors = doctorDao.selectDoctorByRid(rid);
        return doctors;
    }

    @Override
    public boolean update(Doctor doctor) {
        return doctorDao.change(doctor)>0;
    }

    @Override
    public Doctor selectByDoctorId(String doctorId) {
        Doctor doctor = doctorDao.selectDoctorByDoctorId(doctorId);
        return doctor;
    }

    @Override
    public void flush() {
    }
}
