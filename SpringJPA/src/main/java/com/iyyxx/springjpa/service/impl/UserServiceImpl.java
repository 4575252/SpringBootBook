package com.iyyxx.springjpa.service.impl;

import com.iyyxx.springjpa.dao.UserRepository;
import com.iyyxx.springjpa.entity.User;
import com.iyyxx.springjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: UserServiceImpl
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/11/0011 15:31:00
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByNameContaining(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getByName(String name) {
        return userRepository.fingByName(name);
    }

    @Override
    public List<User> findByBirthDayNative(String birthDay) {
        return userRepository.findByBirthDayNative(birthDay);
    }

    @Override
    public Page<User> findAll(String property,
                              Sort.Direction direction,
                              Integer page,
                              Integer size) {
        Pageable pageable = PageRequest.of(page, size, direction, property);
        return userRepository.findAll(pageable);
    }
}
