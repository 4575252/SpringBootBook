package com.iyyxx.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/9/0009 14:15:18
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "Hello Spring MVC";
    }
}
