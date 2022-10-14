package com.cle.service.Impl;

import com.cle.dao.RoomDao;
import com.cle.domain.Room;
import com.cle.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;

    @Override
    public List<Room> selectAll() {
        List<Room> rooms = roomDao.selectAll();
        return rooms;
    }

    @Override
    public boolean add(Room room) {
        return roomDao.add(room)>0;
    }

    @Override
    public boolean delete(String rid) {
        return roomDao.delete(rid)>0;
    }

    @Override
    public Room selectByRid(String rid) {
        Room room = roomDao.selectByRid(rid);
        return room;
    }

    @Override
    public boolean update(Room room) {
        return roomDao.change(room)>0;
    }
}
