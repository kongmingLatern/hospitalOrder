package com.cle.service;

import com.cle.pojo.Doctor;
import com.cle.pojo.PageBean;
import com.cle.pojo.User;

import java.util.List;

public interface DoctorService {
    List<Doctor> selectAll();

    int add(Doctor doctor);

    int delete(String doctorId);

    /**
     * @param currentPage 当前页码
     * @param pageSize    每页展示条数
     * @return
     */
    PageBean<Doctor> selectByPage(int currentPage, int pageSize);
}
