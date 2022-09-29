package com.cle.pojo;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
public class Order {
    private String orderId;
    private String uid;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date orderTime;
    private String orderRoom;
    private int isCancel;
    private int isFinish;

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderRoom() {
        return orderRoom;
    }

    public void setOrderRoom(String orderRoom) {
        this.orderRoom = orderRoom;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", uid='" + uid + '\'' +
                ", orderTime=" + orderTime +
                ", orderRoom='" + orderRoom + '\'' +
                ", isCancel=" + isCancel +
                ", isFinish=" + isFinish +
                '}';
    }
}
