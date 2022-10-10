package com.cle.mapper;

import com.cle.pojo.Room;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoomMapper {
    /**
     * 查询科室
     *
     * @return
     */
    @Select("select * from ROOM")
    List<Room> selectAll();

    /**
     * 插入科室
     *
     * @param room
     * @return
     */
    @Insert("INSERT INTO ROOM VALUES (#{rid},#{rname})")
    int add(Room room);

    /**
     * 删除科室
     *
     * @param rid
     * @return
     */
    @Delete("delete from ROOM where rid = #{rid}")
    int delete(String rid);

    /**
     * 根据rid查询科室
     *
     * @param rid
     * @return
     */
    @Select("select * from ROOM where rid = #{rid}")
    Room selectByRid(String rid);

    /**
     * 修改科室
     *
     * @param room
     * @return
     */
    @Update("UPDATE ROOM SET RNAME=#{rname} where rid = #{rid}")
    int change(Room room);
}
