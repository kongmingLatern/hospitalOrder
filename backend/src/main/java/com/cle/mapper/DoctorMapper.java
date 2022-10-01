package com.cle.mapper;

import com.cle.pojo.Doctor;
import com.cle.pojo.User;
import org.apache.ibatis.annotations.*;

import javax.print.Doc;
import java.util.List;

public interface DoctorMapper {
    /**
     * 查询所有医生
     *
     * @return List<Doctor>
     */
    @Select("SELECT * from doctor ")
    List<Doctor> selectAll();

    /**
     * 添加医生
     *
     * @param doctor
     * @return int 添加数量
     */
    @Insert("insert into doctor values (#{doctorId},#{doctorName},#{doctorAge},#{rid},#{position},#{info},#{limitCount})")
    int add(Doctor doctor);

    /**
     * 分页查询
     *
     * @param begin 开始查询
     * @param size  查询条数
     * @return
     */
    @Select("SELECT * FROM doctor LIMIT #{begin} , #{size}")
    List<Doctor> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询所有用户条数
     *
     * @return int 用户条数
     */
    @Select("select count(*) from doctor")
    int count();

    /**
     * 删除医生
     *
     * @param doctorId
     * @return int 删除条数
     */
    @Delete("DELETE FROM DOCTOR WHERE DOCTORID = #{doctorId}")
    int delete(String doctorId);

    /**
     * 根据rid查询医生
     *
     * @param rid
     * @return
     */
    @Select("SELECT * FROM DOCTOR WHERE RID = #{rid}")
    List<Doctor> selectDoctorByRid(String rid);

    /**
     * 根据doctorId查询医生
     *
     * @param doctorId
     * @return
     */
    @Select("SELECT * FROM DOCTOR WHERE DOCTORID = #{doctorId}")
    Doctor selectDoctorByDoctorId(String doctorId);

    /**
     * 根据doctorId更新医生数据
     *
     * @param doctor
     * @return
     */
    int change(Doctor doctor);

}