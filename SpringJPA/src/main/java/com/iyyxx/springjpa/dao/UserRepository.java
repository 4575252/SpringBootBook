package com.iyyxx.springjpa.dao;

import com.iyyxx.springjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @className: UserRepository
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/11/0011 15:12:48
 **/
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByNameContaining(String name);

    // 根据名称查询
    @Query("select u from User u where u.name = ?1")
    User fingByName(String name);

    // 根据名称模糊查询
    @Query("select u from User u where u.name like %?1")
    List<User> findByName(String name);


    // 根据名称模糊查询
    @Query(value = "select * from user where birth_day =:birthDay",nativeQuery = true)
    List<User> findByBirthDayNative(@Param("birthDay") String birthDay);
}