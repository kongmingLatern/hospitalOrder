package com.cle.controller;

import com.cle.exception.BusinessException;
import com.cle.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author kele
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //系统异常
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        //记录日志
        //发送消息
        //发送邮件,ex对象发送
        return new Result(ex.getCode(),null,ex.getMessage());
    }
    //业务异常
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){
        //记录日志
        //发送消息
        //发送邮件
        return new Result(ex.getCode(),null,ex.getMessage());
    }
    //其他异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        //记录日志
        //发送消息
        //发送邮件
        return new Result(com.cle.controller.Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙，稍后再试！");
    }
}
