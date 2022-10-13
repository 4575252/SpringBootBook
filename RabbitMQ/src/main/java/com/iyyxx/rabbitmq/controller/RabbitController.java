package com.iyyxx.rabbitmq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RabbitController
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/13/0013 10:59:22
 **/
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


    @PostMapping("/work")
    @ApiOperation("工作队列模式")
    public void sendWork(String routingKey, String message){
        for (int i = 1; i < 11; i++) {
            rabbitTemplate.convertAndSend(routingKey, "第"+i+"条消息："+message);
        }
    }


    @PostMapping("/fanout")
    @ApiOperation("广播模式")
    public void sendFanout(String exchange, String message){
        log.info("{},{}", exchange,message);
        for (int i = 1; i < 11; i++) {
            rabbitTemplate.convertAndSend(exchange, "", "第"+i+"条消息："+message);
        }
    }

    @PostMapping("/direct")
    @ApiOperation("路由模式、动态路由模式")
    public void sendDirect(String exchange, String routingKey, String message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
