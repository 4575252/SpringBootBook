# 关于本项目
[![Language](https://img.shields.io/badge/Language-Java_8_121-007396?color=orange&logo=java)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Framework-Spring_Boot_2.7.4-6DB33F?logo=spring)](https://github.com/4575252/SpringBootBook)

本文虽然参考文章做实战，但只完成了一半，目前新版的Actuator、SpringBootAdmin与Knife4J有依赖冲突，因此主要[参考这里](https://www.51cto.com/article/699076.html)

>本次实验，用sba做服务端，helloworld和新增的actuator做实验，在actuator上有一些差异，做对比，如果是之前的项目因开启的信息更多，可以对比更多数据！

## 服务端配置
操作过程
- POM配置admin-starter依赖
- 工程入口开启@EnableAdminServer注解
- 补充：如果是同一台机器，配置文件中要修改不同端口，这里用的9001，与客户端的配置一一对应
```xml
 <dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
</dependency>
```

```properties
server.port=9001
```

## 客户端配置
- 引入2个依赖，一个是客户端，一个是增强接口信息也就是actuator
- 配置文件添加服务端信息并开放actuator被采集！

```xml
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.4.3</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
# 当前项目端口号 
server.port=8081
# Spring Boot Admin 监控服务器端地址 
spring.boot.admin.client.url=http://localhost:9001 
# 开启监控所有项
management.endpoints.web.exposure.include=* 
spring.application.name=Actuator
```