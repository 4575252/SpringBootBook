# 关于本项目
[![Framework](https://img.shields.io/badge/TimerTask-SpringTask-green)](https://github.com/4575252/SpringBootBook)
[![Framework](https://img.shields.io/badge/TimeTask-Quartz-blue)](https://github.com/4575252/SpringBootBook)

本章主要演示定时器的操作，本项目主要实验以下内容：
- SpringTask的实验，包括三种定时器FixDelay、Cron、FixRate
- 分布式环境下的quartz处理


## SpringTask
这是单机使用的推荐方案，集成在Spring-Context中，所以不需要其他依赖，三种定时器都是不交叉执行，区别在于场景和效率，具体如下：
- FixDelay特点：严格树立间隔区间
- Cron特点：严格按定时点进行，超时则等待下一个周期
- FixRate特点：效率与计划并重，超时有空位立刻开始，未到时间该等待就等待！
![](http://image.iyyxx.com/i/2022/10/13/6347696663838.png)

Spring Task集成步骤
- 工程主类开启@EnableScheduling注解
- 新增定时器，标注@Component
- 定时器的方法采用 @Scheduled，参数可以选择FixDelay、Cron、FixRate（具体详见代码）

> 小结：Task的计划任务是单线程阻塞的，严格又带点古板

## Quartz
本项目主要是单机版的两种配置方式，持久化分布式方案可参考[这里](https://www.cnblogs.com/summerday152/p/14193968.html)，因目前主流xxl-job由点评网牵头开源,后续再补充。

quartz集成步骤如下：
- pom引入依赖
```xml
<!-- 实现对 Quartz 的自动化配置 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```
- job类设计，这是具体的执行逻辑，继承QuartzJobBean，重写executeInternal方法，另外接口实现方式，可参看上面连接！
```java
public class FirstJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //……
    }
}
```

quartz自动配置，新增一个配置类，填充jobDetail和trigger
```java
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail1() {
        // ……
    }
    @Bean
    public Trigger trigger1() {
        // ……
    }
}

```

quartz手动配置,新建一个bean实现ApplicationRunner接口，重写run方法，实现jobDetail、Cron、Trigger
```java
@Component
public class JobInit implements ApplicationRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(SecondJob.class)
                .withIdentity(ID + " 01")
                .storeDurably()
                .build();
        CronScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(ID + " 01Trigger")
                .withSchedule(scheduleBuilder)
                .startNow() //立即執行一次任務
                .build();
        // 手动将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}


```