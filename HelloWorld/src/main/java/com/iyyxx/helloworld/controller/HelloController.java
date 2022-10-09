package com.iyyxx.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: HelloController
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/8/0008 16:22:39
 **/
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello Spring Boot";
    }
}
