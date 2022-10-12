package com.iyyxx.redisinaction.controller;

import com.iyyxx.redisinaction.dto.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @className: RedisController
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/12/0012 14:26:11
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private User hello;
    private Object object;

    @GetMapping("/hello")
    public User hello() {
        User u = new User("张三", 1,"公号10086");
        redisTemplate.opsForValue().set("hello", u);
        return (User) redisTemplate.opsForValue().get("hello");
    }

    @GetMapping("/jdkSerializer")
    public void jdkSerializer() {
        redisTemplate.opsForValue().set("key","value");
    }

    @GetMapping("/expire")
    public void setExpire(String key) {
        stringRedisTemplate.expire(key, 30, TimeUnit.SECONDS);
    }

    @DeleteMapping("/delete")
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @GetMapping("/get")
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping("/set")
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }
}