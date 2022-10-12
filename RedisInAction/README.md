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
- 补充了全局的异常处理


## 关于分布式锁
代码中用了三种方案，方案三已经很不错了，官方推荐分布式锁的解决方案是Redission产品

>当然，演示中的方案二是旧版本缺陷，新版本已经是原子性命令了，所以方案2,3都OK

![](http://image.iyyxx.com/i/2022/10/12/63467d5ad5996.png)


## 全局异常处理
这边用到了@RestControllerAdvice 注解，对全局异常及其子类分别捕获，后续可以自由扩展非常的奈斯，特别是对返回类型和错误编号、信息做了枚举。

```java
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Boolean> globalException(Exception e) {
        Result<Boolean> result = new Result<>();
        result.setCode(MessageEnum.ERROR.getCode());
        result.setMessage(e.getMessage() == null ? MessageEnum.ERROR.getMessage() : e.getMessage());
        log.error(e.getMessage(),e);
        return result;
    }

    //这里可以根据不同的异常类型进行捕获，然后返回接口错误信息，具体的代码还可以用枚举进行罗列！
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Boolean> apiException(IllegalArgumentException e) {
        Result<Boolean> result = new Result<>();
        result.setCode(MessageEnum.ERROR.getCode());
        result.setMessage("非法参数 "+e.getMessage());
        log.error(e.getMessage(),e);
        return result;
    }

}
```