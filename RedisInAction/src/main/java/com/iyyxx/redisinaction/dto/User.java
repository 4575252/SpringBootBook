package com.iyyxx.redisinaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: User
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/12/0012 14:44:24
 **/
@Data
@AllArgsConstructor
public class User implements Serializable {
    String name;
    int age;
    String id;
}
