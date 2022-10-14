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

        return new Result(ex.getCode(),null,ex.getMessage());
    }
    //业务异常
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){

        return new Result(ex.getCode(),null,ex.getMessage());
    }
    //其他异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){

        return new Result(Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙，稍后再试！");
    }
}
