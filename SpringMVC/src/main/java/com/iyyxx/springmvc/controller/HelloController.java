package com.iyyxx.springmvc.controller;

import com.iyyxx.springmvc.entity.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @className: HelloController
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022-10-07 11:33:26
 **/
@Api("Hello控制器")
@Slf4j
@RestController
public class HelloController {

    @ApiOperation(value = "无注解方式", notes = "多个参数，多种的查询参数类型")
    @GetMapping("/hello")
    public String hello() {
        log.info("通配符测试.{}.{}.{}", "A", "B", "C");
        return "Hello Spring Boot";
    }

    @ApiOperation("@RequestParam方式")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "name", value = "用户名称", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", required = false)})
    @GetMapping("/requestParam")
    public User requestParam(String name, int age) {
        User u = new User(name, ++age);
        return u;
    }

    @ApiOperation("@PathVariable方式")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "用户名称", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", required = false)})
    @GetMapping("/pathVariable/{name}/{age}")
    public User pathVariable(@PathVariable String name, @PathVariable int age) {
        User u = new User(name, --age);
        return u;
    }

    @ApiOperation("@RequestBody方式")
    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户实体，参数用", required = false)})
    @PostMapping("/requestBody")
    public User requestBody(@RequestBody User user) {
        user.setAge(user.getAge() - 2);
        return user;
    }
}
