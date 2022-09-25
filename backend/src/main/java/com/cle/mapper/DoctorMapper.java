package com.cle.mapper;

import com.cle.pojo.Doctor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DoctorMapper {

    @Select("SELECT * from doctor ")
    public List<Doctor> selectAll();

    @Insert("insert into doctor values (#{doctorId},#{doctorName},#{doctorAge},#{rid},#{position},#{info},#{limitCount})")
    public void add(Doctor doctor);

    @Delete("delete from doctor where doctorId=#{doctorId}")
    public void delete(String doctorId);
}
