package com.cle.service;

import com.cle.pojo.Doctor;
import java.util.List;

public interface DoctorService {
    public List<Doctor> selectAll();
    public void add(Doctor doctor);
}
