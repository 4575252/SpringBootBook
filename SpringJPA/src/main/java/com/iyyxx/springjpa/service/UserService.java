package com.iyyxx.springjpa.service;

import com.iyyxx.springjpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @className: UserService
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/11/0011 15:16:54
 **/
public interface UserService {

    public User addOrUpdate(User user);

    public User get(int id);

    public void deleteById(int id);

    public List<User> findByNameContaining(String name);

    User getByName(String name);

    List<User> findByBirthDayNative(String birthDay);

    Page<User> findAll(String property,
                       Sort.Direction direction,
                       Integer page,
                       Integer size);
}
