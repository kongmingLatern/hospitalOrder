package com.cle.pojo;

import java.util.Date;

/**
 * @author kele
 */
public class User {
    private String uid;//用户Id
    private String userName;//用户名字
    private String password;//密码
    private int age;//用户年龄
    private String realName;//用户真实名字
    private int cancelCount;//取消次数
    private int isAllow;//是否违规
    private int isAuth;//是否管理员
    private Date firstCancelTime;//第一次取消时间
    private Date banTime;//封禁时间

    public User() {
    }

    public Date getFirstCancelTime() {
        return firstCancelTime;
    }

    public void setFirstCancelTime(Date firstCancelTime) {
        this.firstCancelTime = firstCancelTime;
    }

    public Date getBanTime() {
        return banTime;
    }

    public void setBanTime(Date banTime) {
        this.banTime = banTime;
    }

    public int getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(int isAuth) {
        this.isAuth = isAuth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(int cancelCount) {
        this.cancelCount = cancelCount;
    }

    public int getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(int isAllow) {
        this.isAllow = isAllow;
    }
}
