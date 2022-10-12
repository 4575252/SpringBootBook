package com.iyyxx.springmybatisplus.service.impl;

import com.iyyxx.springmybatisplus.entity.User;
import com.iyyxx.springmybatisplus.mapper.UserMapper;
import com.iyyxx.springmybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author 林智辉
 * @blog http://iyyxx.com
 * @since 2022-10-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
