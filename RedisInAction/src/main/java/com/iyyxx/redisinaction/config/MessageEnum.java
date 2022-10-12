package com.iyyxx.redisinaction.config;

import lombok.Getter;

/**
 * @className: MessageEnum
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/12/0012 17:24:00
 **/
@Getter
public enum MessageEnum {
    ERROR(500, "系统错误"),
    SUCCESS(0, "操作成功！"),
    ;
    private final Integer code;
    private final String message;

    MessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
