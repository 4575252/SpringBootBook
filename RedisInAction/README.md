# 关于本项目
[![Language](https://img.shields.io/badge/Language-Java_8_121-007396?color=silver&logo=java)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Framework-Spring_Boot_2.7.4-6DB33F?logo=spring)](https://github.com/4575252/SpringBootBook)
[![Lombok](https://img.shields.io/badge/Lombok-Spring_Boot_1.18.20-pink?logo=lombok)](https://github.com/4575252/SpringBootBook)
[![Swagger2](https://img.shields.io/badge/Swagger2-Knife4j_3.0.2-blue?logo=swagger)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/redis-jedis-orange)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Serializer-fastjson-red)](https://github.com/4575252/SpringBootBook)

本章继续在springboot基础上对redis进行实验，本项目主要实验以下内容：
- 累积的基础框架，lombok、knife4j、springboot的web starter。
- 集成SpringDataRedis，并摒弃自带的lettuce客户端，继续使用jedis！
- 序列化器采用阿里的fastjson，并对键值对的乱码做了修正！


## 关于分布式锁
代码中用了三种方案，方案三已经很不错了，官方推荐分布式锁的解决方案是Redission产品

>当然，演示中的方案二是旧版本缺陷，新版本已经是原子性命令了，所以方案2,3都OK

![](http://image.iyyxx.com/i/2022/10/12/63467d5ad5996.png)

