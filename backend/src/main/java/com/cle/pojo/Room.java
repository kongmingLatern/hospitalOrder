package com.cle.pojo;

//科室实体类
public class Room {
    private String rid;//房间Id
    private String rname;//科室名称

    public Room() {
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
