package com.cle.controller;

import com.cle.domain.Room;
import com.cle.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping
    public Result getAll() {
        List<Room> rooms = roomService.selectAll();
        Integer code = rooms != null ? Code.GET_OK : Code.GET_ERR;
        String msg = rooms != null ? "查询成功" : "查询失败";
        return new Result(code, rooms, msg);
    }

    @PostMapping
    public Result save(@RequestBody Room room) {
        boolean flag = roomService.add(room);
        return new Result(flag?Code.SAVE_OK:Code.SAVE_ERR,flag,flag?"添加成功":"添加失败");
    }

    @DeleteMapping("/{rid}")
    public Result delete(@PathVariable String rid) {
        boolean flag = roomService.delete(rid);
        return new Result(flag?Code.DELETE_OK:Code.DELETE_ERR,flag,flag?"删除成功":"删除失败");
    }

    @GetMapping("/getByRid/{rid}")
    public Result getByRid(@PathVariable String rid) {
        Room room = roomService.selectByRid(rid);
        Integer code = room != null ? Code.GET_OK : Code.GET_ERR;
        String msg = room != null ? "查询成功" : "查询失败";
            return new Result(code, room, msg);
    }

    @PutMapping
    public Result update(@RequestBody Room room) {
        boolean flag = roomService.update(room);
        return new Result(flag?Code.UPDATE_OK:Code.UPDATE_ERR,flag,flag?"修改成功":"修改失败");
    }
}
