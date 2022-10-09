package com.iyyxx.springmvc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: User
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022-10-07 12:30:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户实体")
public class User {
    @ApiModelProperty(value = "用户名", example = "张三")
    String name;
    int age;
}
