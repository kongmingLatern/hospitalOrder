package com.cle.service;

import com.cle.pojo.Doctor;
import com.cle.pojo.PageBean;
import com.cle.pojo.User;

import java.util.List;

public interface DoctorService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Doctor> selectAll();

    /**
     * 添加医生
     *
     * @param doctor
     * @return
     */
    int add(Doctor doctor);

    /**
     * 删除医生
     *
     * @param doctorId
     * @return
     */
    int delete(String doctorId);

    /**
     * @param currentPage 当前页码
     * @param pageSize    每页展示条数
     * @return
     */
    PageBean<Doctor> selectByPage(int currentPage, int pageSize);

    /**
     * 根据rid查询医生
     *
     * @param rid
     * @return
     */
    List<Doctor> selectDoctorByRid(String rid);

    /**
     * 更新医生信息
     *
     * @param doctor
     * @return
     */
    int change(Doctor doctor);

    /**
     * 根据doctorId查询医生
     *
     * @param doctorId
     * @return
     */
    Doctor selectByDoctorId(String doctorId);

    /**
     * 刷新医生每日预约
     */
    void flush();
}
