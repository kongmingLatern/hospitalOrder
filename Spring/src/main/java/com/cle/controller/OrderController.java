package com.cle.controller;

import com.cle.domain.Order;
import com.cle.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public Result selectAll()  {
        List list = orderService.selectAll();
        Integer code = list != null ? Code.GET_OK : Code.GET_ERR;
        String msg = list != null ? "查询成功" : "查询失败";
        return new Result(code, list, msg);
    }

    @PostMapping
    public Result save(@RequestBody Order order){
        boolean flag = orderService.add(order);
        return new Result(flag?Code.SAVE_OK:Code.SAVE_ERR,flag,flag?"添加成功":"添加失败");
    }

    @DeleteMapping("/{orderId}")
    public Result delete(String orderId){
        boolean flag = orderService.delete(orderId);
        return new Result(flag?Code.DELETE_OK:Code.DELETE_ERR,flag,flag?"删除成功":"删除失败");
    }

    @GetMapping("/getByUid/{uid}")
    public Result getByUid(@PathVariable String uid){
        List<Order> orders = orderService.selectByUid(uid);
        Integer code = orders != null ? Code.GET_OK : Code.GET_ERR;
        String msg = orders != null ? "查询成功" : "查询失败";
        return new Result(code, orders, msg);
    }

    @GetMapping("/getByOrderId/{orderId}")
    public Result getByOrderId(@PathVariable String orderId)
    {
        Map map = orderService.selectByOrderId(orderId);
        Integer code = map != null ? Code.GET_OK : Code.GET_ERR;
        String msg = map != null ? "查询成功" : "查询失败";
        return new Result(code, map, msg);
    }

    @PutMapping
    public Result update(@RequestBody Order order){
        boolean flag = orderService.update(order);
        return new Result(flag?Code.UPDATE_OK:Code.UPDATE_ERR,flag,flag?"修改成功":"修改失败");
    }

    @PostMapping("/cancelOrder")
    public Result cancelOrder(@RequestBody Order order){
        boolean flag = orderService.cancelOrder(order.getOrderId());
        return new Result(flag?Code.UPDATE_OK:Code.UPDATE_ERR,flag,flag?"取消成功":"取消失败");
    }
}
