package com.cle.mapper;

import com.cle.pojo.Doctor;
import com.cle.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DoctorMapper {

    @Select("SELECT * from doctor ")
    List<Doctor> selectAll();

    @Insert("insert into doctor values (#{doctorId},#{doctorName},#{doctorAge},#{rid},#{position},#{info},#{limitCount})")
    int add(Doctor doctor);

    /**
     * 分页查询
     *
     * @param begin
     * @param size
     * @return
     */
    @Select("SELECT * FROM doctor LIMIT #{begin} , #{size}")
    List<Doctor> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询所有用户条数
     *
     * @return
     */
    @Select("select count(*) from doctor")
    int count();

    /**
     * 删除医生
     *
     * @param uid
     */
    @Delete("DELETE FROM DOCTOR WHERE DOCTORID = #{uid}")
    int delete(@Param("uid") String uid);
}
