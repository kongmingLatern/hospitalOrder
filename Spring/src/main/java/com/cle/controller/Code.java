package com.cle.controller;

public class Code {
    public static final Integer SAVE_OK = 20011;//POST请求存储成功
    public static final Integer DELETE_OK = 20021;//DELETE请求删除成功
    public static final Integer UPDATE_OK = 20031;//PUT请求更新成功
    public static final Integer GET_OK = 20041;//Get请求查询成功

    public static final Integer SAVE_ERR = 20010;//POST请求存储失败
    public static final Integer DELETE_ERR = 20020;//DELETE请求删除失败
    public static final Integer UPDATE_ERR = 20030;//PUT请求更新失败
    public static final Integer GET_ERR = 20040;//Get请求查询失败

    public static final Integer SYSTEM_ERR = 50001;//系统错误
    public static final Integer BUSINESS_ERR = 60002;//业务错误
    public static final Integer TIME_OUT_ERR = 50002;//超时
    public static final Integer SYSTEM_UNKNOW_ERR = 59999;//未知错误

    public static final Integer LOGIN_OK = 20001;//登录成功
    public static final Integer LOGIN_ERR = 20000;//登录失败
    public static final Integer NOT_ALLOW = 20010;//登录成功但是被封禁
    public static final Integer IS_AUTH = 20021;//登录成功是管理员

}
