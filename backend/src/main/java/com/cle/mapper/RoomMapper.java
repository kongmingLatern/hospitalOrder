package com.cle.mapper;

import com.cle.pojo.Room;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoomMapper {
    /**
     * 查询科室
     *
     * @return
     */
    @Select("select * from ROOM")
    List<Room> selectAll();

    @Insert("INSERT INTO ROOM VALUES (#{rid},#{rname})")
    int add(Room room);

    @Delete("delete from ROOM where rid = #{uid}")
    int delete(@Param("uid") String uid);
}
