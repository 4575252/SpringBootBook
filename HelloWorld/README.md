# 关于本项目
[![Language](https://img.shields.io/badge/Language-Java_8_121-007396?color=orange&logo=java)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/Framework-Spring_Boot_2.7.4-6DB33F?logo=spring)](https://github.com/4575252/SpringBootBook)

主要是采用SpringBoot技术实现helloworld，这是java惯例，不能免俗。

虽然做过无数次，过程也简单，所以整合了IDEA使用技巧，这也是本书第二章的主要内容。

## HelloWorld
操作过程
- 在父工程中，添加model，选择spring initializr，输入相关信息，勾选Spring Web组件
- 添加HelloController
  - 填上类注解
  - 填上访问方法及mapping注解，这里采用query方式

```java
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello Spring Boot";
    }
}
```
访问测试： http://localhost:8080/hello

效果如下：
![](http://image.iyyxx.com/i/2022/10/08/63413470db230.png)

## IDEA使用技巧
- 用一个项目存放多个工程，统一维护管理，就是本项目用到的技术啦
- 调整字体、皮肤、显示行号、方法分隔符、自定义工具栏、rainbow括号插件

以上内容可以在作者博客中找到 http://www.iyyxx.com
