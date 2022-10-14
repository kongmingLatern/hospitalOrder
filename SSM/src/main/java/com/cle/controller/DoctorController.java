package com.cle.controller;

import com.cle.domain.Doctor;
import com.cle.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public Result getAll() {
        List<Doctor> doctors = doctorService.selectAll();
        Integer code = doctors != null ? Code.GET_OK : Code.GET_ERR;
        String msg = doctors != null ? "查询成功" : "查询失败";
        return new Result(code, doctors, msg);
    }
    @PostMapping
    public Result save(@RequestBody Doctor doctor){
        boolean flag = doctorService.add(doctor);
        return new Result(flag?Code.SAVE_OK:Code.SAVE_ERR,flag,flag?"添加成功":"添加失败");
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id)
    {
        boolean flag = doctorService.delete(id);
        return new Result(flag?Code.DELETE_OK:Code.DELETE_ERR,flag,flag?"删除成功":"删除失败");
    }
    @GetMapping("/getByRid/{rid}")
    public Result getByRid(@PathVariable String rid){
        List<Doctor> doctors = doctorService.selectDoctorByRid(rid);
        Integer code = doctors != null ? Code.GET_OK : Code.GET_ERR;
        String msg = doctors != null ? "查询成功" : "查询失败";
        return new Result(code, doctors, msg);
    }

    @PutMapping
    public Result update(@RequestBody Doctor doctor){
        boolean flag = doctorService.update(doctor);
        return new Result(flag?Code.UPDATE_OK:Code.UPDATE_ERR,flag,flag?"修改成功":"修改失败");
    }
    @GetMapping("/getByDoctorId/{doctorId}")
    public Result getByDoctorId(@PathVariable String doctorId)
    {
        Doctor doctor = doctorService.selectByDoctorId(doctorId);
        Integer code = doctor != null ? Code.GET_OK : Code.GET_ERR;
        String msg = doctor != null ? "查询成功" : "查询失败";
        return new Result(code, doctor, msg);
    }


}
