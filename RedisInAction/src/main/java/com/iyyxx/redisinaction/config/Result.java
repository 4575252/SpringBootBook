package com.iyyxx.redisinaction.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: Result
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/12/0012 17:23:36
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(MessageEnum.SUCCESS.getCode(), MessageEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> error() {
        return error(MessageEnum.ERROR);
    }

    public static <T> Result<T> error(MessageEnum messageEnum) {
        return new Result<>(messageEnum.getCode(),messageEnum.getMessage(),null);
    }

    public static <T> Result<T> error(String message) {
        return error(message, MessageEnum.ERROR.getCode());
    }

    protected static <T> Result<T> error(String message,Integer code) {
        return new Result<>(code,message,null);
    }

}