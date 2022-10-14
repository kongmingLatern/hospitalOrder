package com.cle.service;

import com.cle.domain.Doctor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface DoctorService {
    /**
     * 查询所有
     *
     * @return
     */
    List selectAll();

    /**
     * 添加医生
     *
     * @param doctor
     * @return
     */
    boolean add(Doctor doctor);

    /**
     * 删除医生
     *
     * @param doctorId
     * @return
     */
    boolean delete(String doctorId);


    /**
     * 根据rid查询医生
     *
     * @param rid
     * @return
     */
    List selectDoctorByRid(String rid);

    /**
     * 更新医生信息
     *
     * @param doctor
     * @return
     */
    boolean update(Doctor doctor);
    /**
     * 根据doctorId查询医生
     *
     * @param doctorId
     * @return
     */
    Map selectByDoctorId(String doctorId);

    /**
     * 刷新医生每日预约
     */
    void flush();
}
