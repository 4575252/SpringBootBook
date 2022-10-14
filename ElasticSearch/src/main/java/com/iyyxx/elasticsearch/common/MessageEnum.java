package com.iyyxx.elasticsearch.common;

import lombok.Getter;

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