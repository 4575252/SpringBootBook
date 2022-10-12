package com.iyyxx.springjpa.controller;

import com.iyyxx.springjpa.dao.UserRepository;
import com.iyyxx.springjpa.entity.User;
import com.iyyxx.springjpa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "用户接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据id获取用户信息")
    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @ApiOperation(value = "根据名字获取用户信息")
    @GetMapping("/name")
    public List<User> getByName(String name) {
        return userService.findByNameContaining(name);
    }

    @ApiOperation(value = "获取用户列表（分页）")
    @GetMapping
    public Page<User> list(@RequestParam(defaultValue = "id") String property,
                           @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                           @RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "10") Integer size) {

        return userService.findAll(property, direction, page, size);
    }
//
//    @ApiOperation(value = "根据名字查询（分页）")
//    @GetMapping("/page/{name}")
//    public Page<User> queryByName(@PathVariable String name,
//                                  @RequestParam(defaultValue = "id") String property,
//                                  @RequestParam(defaultValue = "ASC") Sort.Direction direction,
//                                  @RequestParam(defaultValue = "0") Integer page,
//                                  @RequestParam(defaultValue = "10") Integer size) {
//
//        Pageable pageable = PageRequest.of(page, size, direction, property);
//
//        return userRepository.findByNameContaining(name, pageable);
//    }

    @ApiOperation(value = "创建用户")
    @PostMapping
    @Transactional
    public User create(@RequestBody User user) {
        return userService.addOrUpdate(user);
    }

    @ApiOperation(value = "更新用户")
    @PutMapping
    public User update(@RequestBody User user) {
        return userService.addOrUpdate(user);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @ApiOperation(value = "根据name获取用户信息")
    @GetMapping("/name2")
    public User getByName2(String name) {
        return userService.getByName(name);
    }

    //    @ApiOperation(value = "删除所有用户")
//    @DeleteMapping
//    public int delete() {
//        return userRepository.delete();
//    }
    @ApiOperation(value = "根据生日获取用户信息")
    @GetMapping("/birthDay")
    public List<User> findByBirthDayNative(String birthDay) {
        return userService.findByBirthDayNative(birthDay);
    }

}
