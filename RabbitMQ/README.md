# 关于本项目
[![Framework](https://img.shields.io/badge/MQ-RabbitMQ_3.8.5-green)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/AMQP-Spring-blue)](https://github.com/4575252/SpringBootBook)

本章主要演示RabbitMQ的操作，本项目主要实验以下内容：
- RabbitMQ安装，[参考这里](https://blog.csdn.net/weixin_40584261/article/details/106826044)
- 


## RabbitMQ集成
- pom文件引入spring-boot-starter-amqp坐标
- application文件配置rabbit服务器信息，当然需要在rabbitmq上提前开好vs、账号、口令和权限啦


## 简单模式实战
通过配置生产端、消费端进行一进一出的操作，使用到1个连接（单机）、2个channel、1个queue

**配置一个生产端：** controller调用rabbitTemplate接收post进行提交，用swagger测试，rabbitmq上查看效果
```java
@Slf4j
@Api("Hello控制器")
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostMapping
    @ApiOperation("简单模式")
    public void send(String routingKey, String message){
        rabbitTemplate.convertAndSend(routingKey, message);
    }
}
```

**配置一个消费端：** 监听器，接收消息并打印
```java
@Slf4j
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloConsumer {
    @RabbitHandler
    public void receive(String message){
        log.info(message);
    }
}
```


## 工作队列模式
设置2个消费端，可看到消息被交替消费：
- 服务端代码无特别改动
- 客户端代码有调整，监听放在方法上！
```java
@Slf4j
@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiveOne(String message){
        log.info("{} 被消费端One消费",message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiveTwo(String message){
        log.info("{} 被消费端Two消费",message);
    }
}
```


## 工作队列模式之能者多劳模式
在上面的例子中，如果两个处理过程有差异，比如用Thread.sleep进行设置执行1秒和4秒，但实际捕获消息还是5:5，优化方法如下：
- 自定义容器工厂类，关键在于在工厂中设置步长为0，默认是250
```java
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        // MANUAL 手动确认
        // AUTO  消费完自动确认（Spring 确认）
        // NONE  消息分配后就确认（rabbitmq 确认）
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // 拒绝策略,true 回到队列 false 丢弃，默认为 true
        factory.setDefaultRequeueRejected(true);
        // 默认的 PrefetchCount是 250
        factory.setPrefetchCount(0);    //关键
        return factory;
    }
}
```


## 广播模式 Fanout
类似工作队列1对多，但每个人都是拥有全部消息的消费权限，也就是数据一样多，互不干扰，多通道复制！

**生产端有小修改**
```java
// 调用的是3个参数的方法
// rabbitTemplate.convertAndSend(exchange, "", "第"+i+"条消息："+message);
```

**客户端变化**
```java
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "fanout", type = "fanout")))
    public void receiveOne(String message) {
        //……
    }
```


## 路由模式 Direct
根据【exchange】、【routingKey】进行分发，找不到的就丢弃了，可以绑定多个key！


## 动态路由模式 Topic
与路由模式类似，不过支持通配符，如下：
- *代表一个单词
- #代表一个或多个单词

> RabbitMQ有7种工作模式，这里演示了5种和工作队列的性能优化模式，远程模式和生产者确认模式没有在这里演示