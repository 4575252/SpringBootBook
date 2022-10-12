package com.iyyxx.redisinaction.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @className: GlobalExceptionHandler
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/12/0012 17:23:12
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Boolean> globalException(Exception e) {
        Result<Boolean> result = new Result<>();
        result.setCode(MessageEnum.ERROR.getCode());
        result.setMessage(e.getMessage() == null ? MessageEnum.ERROR.getMessage() : e.getMessage());
        log.error(e.getMessage(),e);
        return result;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Boolean> apiException(IllegalArgumentException e) {
        Result<Boolean> result = new Result<>();
        result.setCode(MessageEnum.ERROR.getCode());
        result.setMessage("非法参数 "+e.getMessage());
        log.error(e.getMessage(),e);
        return result;
    }

}