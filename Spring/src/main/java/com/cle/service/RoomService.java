package com.cle.service;

import com.cle.domain.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
    boolean add(Room room);

    /**
     * 删除科室
     *
     * @param rid
     * @return
     */
    boolean delete(String rid);

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
    boolean update(Room room);
}
