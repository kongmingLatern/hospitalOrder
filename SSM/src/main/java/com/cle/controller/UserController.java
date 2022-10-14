package com.cle.controller;

import com.cle.domain.User;
import com.cle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public Result getAll(){
        List<User> users = userService.selectAll();
        Integer code = users != null ? Code.GET_OK : Code.GET_ERR;
        String msg = users != null ? "查询成功" : "查询失败";
        return new Result(code, users, msg);
    }
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        boolean flag = userService.add(user);
        return new Result(flag?Code.SAVE_OK:Code.SAVE_ERR,flag,flag?"添加成功":"添加失败");
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        boolean flag = userService.update(user);
        return new Result(flag?Code.UPDATE_OK:Code.UPDATE_ERR,flag,flag?"修改成功":"修改失败");
    }

    @DeleteMapping("/{uid}")
    public Result delete(@PathVariable String uid) {
        boolean flag = userService.delete(uid);
        return new Result(flag?Code.DELETE_OK:Code.DELETE_ERR,flag,flag?"删除成功":"删除失败");
    }
    @GetMapping("/getByUid/{uid}")
    public Result getByUid(@PathVariable String uid){
        User user = userService.selectByUid(uid);
        Integer code = user != null ? Code.GET_OK : Code.GET_ERR;
        String msg = user != null ? "查询成功" : "查询失败";
        return new Result(code, user, msg);
    }
    @PostMapping("/login")
    public Result login(@RequestBody User user)
    {
        User user1 = userService.login(user);
        if(user1==null||!(user1.getPassword().equals(user.getPassword())))
        {
            return new Result(Code.LOGIN_ERR,null,"用户名或者密码错误");
        }
        if(user1.getIsAllow()==1)
        {
            return new Result(Code.NOT_ALLOW,null,"账号封禁");
        }
        if(user1.getIsAuth()==1)
        {
            return new Result(Code.IS_AUTH,null,"登录成功");
        }
        return new Result(Code.LOGIN_OK,null,"登录成功");
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        boolean flag = userService.add(user);
        return new Result(flag?Code.SAVE_OK:Code.SAVE_ERR,flag,flag?"注册成功":"注册失败");
    }
}
