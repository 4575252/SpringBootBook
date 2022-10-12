# 关于本项目
[![Language](https://img.shields.io/badge/Language-Java_8_121-007396?color=silver&logo=java)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Framework-Spring_Boot_2.7.4-6DB33F?logo=spring)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Persistence-MyBatis_Plus_3.4.2-orange?logo=MyBatis)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/ConnectPool-druid_1.2.5-red?logo=MyBatis)](https://github.com/4575252/SpringBootBook)
[![Lombok](https://img.shields.io/badge/Lombok-Spring_Boot_1.18.20-pink?logo=lombok)](https://github.com/4575252/SpringBootBook)
[![Swagger2](https://img.shields.io/badge/Swagger2-Knife4j_3.0.2-blue?logo=swagger)](https://github.com/4575252/SpringBootBook)

MyBatis一直是东方IT圈的首选，这边采用mbp扩展包进行实验，本项目主要实验以下内容：
- 累积的基础框架，lombok、knife4j、springboot的web starter。
- 集成MyBatisPlus和Generator，可快速生成mvc相关代码！
- 集成freemarker模板引擎，方便自动生产常用的crud相关前端控制器代码！
- 集成druid，并配置访问口令，做操作实战！可方便查看sql的执行情况、性能指标，session数据及web请求等众多常用信息！

## 实验
代码较多，直接查看源码更直观，这里罗列一下主要清单：
- MysqlGenerator，执行main方法，输入表名并回车，连接数据库反向工程生成相关代码！
- templates下的ftl模板

附：数据user表的脚本
```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `name` varchar(20)  NOT NULL COMMENT '姓名',
  `email` varchar(50)  DEFAULT NULL COMMENT '邮箱',
  `birth_day` date NULL DEFAULT NULL COMMENT '生日',
  `creator` varchar(20)  NULL DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(20)  NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `uk_email`(`email`)
) ENGINE = InnoDB default CHARACTER SET = utf8 COMMENT '用户信息';

```

