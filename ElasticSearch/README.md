# 关于本项目
[![Framework](https://img.shields.io/badge/Nosql-ElasticSearch_8.4.3-green)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/AMQP-Spring-blue)](https://github.com/4575252/SpringBootBook)

本章主要演示RabbitMQ的操作，本项目主要实验以下内容：
- RabbitMQ安装，[参考这里](https://blog.csdn.net/weixin_40584261/article/details/106826044)
- 

## es的概念
- index，也就是数据库
- type，已弱化，类似表，目前就一张大表，即_doc
- document，行记录
- field，字段，相当于column
- mapping，字段定义，即scheme，如主外键、索引等

## ES安装
大部分资料的[来源参考](https://cloud.tencent.com/developer/article/1804215)

```shell
cd ~
wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.14.2-linux-x86_64.tar.gz
rm -fr /usr/local/elasticsearch-7.14.2/ /usr/local/es
tar zxvf elasticsearch-7.14.2-linux-x86_64.tar.gz -C /usr/local
ln -s /usr/local/elasticsearch-7.14.2/ /usr/local/es

chown -R elasticsearch:elasticsearch /usr/local/es
chown -R elasticsearch:elasticsearch /usr/local/elasticsearch-7.14.2

#创建用户,并开启权限
adduser elasticsearch
echo 'xxx' |passwd --stdin elasticsearch;
echo "elasticsearch hard nofile 65536" >> /etc/security/limits.conf
echo "elasticsearch soft nofile 65536" >> /etc/security/limits.conf
su elasticsearch -c 'ulimit -Hn'
echo "vm.max_map_count=262144" > /etc/sysctl.conf
sysctl -p

# sed -i "s#xpack.security.enabled: true#xpack.security.enabled: false#g" /usr/local/es/config/elasticsearch.yml
# sed -i "s|#network.host: 192.168.0.1|network.host: 0.0.0.0|g" /usr/local/es/config/elasticsearch.yml

#启动 Elasticsearch
su elasticsearch -c '/usr/local/es/bin/elasticsearch -d'
```
补充：最新8.4.3版本差异大，目前主流的springboot2.7.3的starter支持不是很好，这里退回到[7.14.2](https://blog.csdn.net/m0_51510236/article/details/120829135)，顺利集成！需要额外配置文件，具体如下：

```yaml
# config/elasticsearch.yml 
cluster.name: my-application
node.name: node-1
network.host: 192.168.20.140
http.port: 9200
discovery.seed_hosts: ["192.168.20.140"]
cluster.initial_master_nodes: ["node-1"]
```

## es安装检查和基本使用
通过在服务端的shell进行检查
```shell
# 查询服务器信息
curl http://192.168.20.140:9200
# 查询全部索引
curl -X GET 'http://192.168.20.140:9200/_cat/indices?v'

# 测试：文档创建
curl -H "Content-Type:application/json" -X PUT 192.168.20.140:9200/company/employees/1?pretty=true -d '{"id" :1,"name":"admin","password":"123"}'
# 测试：文档查询
curl -X GET 192.168.20.140:9200/company/employees/1?pretty=true
# 测试：文档修改
curl -H "Content-Type:application/json" -X POST 192.168.20.140:9200/company/employees/1 -d '{"id" :1,"name":"admin","password":"111"}'
# 测试：文档删除
curl -X DELETE 192.168.20.140:9200/company/employees/1?pretty=true
```

> 项目中提供了Test代码，采用ElasticSearchRestTemplate，另外还可以在Idea中安装`Cap Elasticsearch Cluster`插件来进行快速使用

测试数据如下：
```json
[
  {
    "id": 1,
    "author": "小刘",
    "title": "spring boot 集成 rabbitMQ",
    "content": "世界上本没有 MQ，需要的人多了，便诞生了 MQ。"
  },
  {
    "id": 2,
    "author": "小水",
    "title": "spring boot 集成 elasticsearch",
    "content": "Elasticsearch 在速度和可扩展性方面都表现出色，而且还能够索引多种类型的内容，这意味着其可用于多种用例"
  },
  {
    "id": 3,
    "author": "小镜",
    "title": "spring boot 集成 Redis",
    "content": "Redis（全称：Remote Dictionary Server 远程字典服务）是一个开源的使用 ANSI C 语言编写、支持网络、基于内存并且可持久化的、Key-Value 数据库。"
  },
  {
    "id": 4,
    "author": "刘水镜",
    "title": "spring boot 集成 Spring Security",
    "content": "Spring Security 是一个标准的基于 Spring 的安全应用，具有安全认证和访问控制能力，功能强大且支持高度定制化的框架。"
  }
]
```