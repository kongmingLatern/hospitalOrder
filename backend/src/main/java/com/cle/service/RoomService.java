package com.cle.service;

import com.cle.pojo.Room;

import java.util.List;

public interface RoomService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Room> selectAll();

    /**
     * 添加科室
     *
     * @param room
     * @return
     */
    int add(Room room);

    /**
     * 删除科室
     *
     * @param uid
     * @return
     */
    int delete(String uid);

    /**
     * 根据rid查询科室
     *
     * @param rid
     * @return
     */
    Room selectByRid(String rid);

    /**
     * 修改科室
     *
     * @param room
     * @return
     */
    int change(Room room);
}
