package com.iyyxx.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: HelloConsumer
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/13/0013 11:03:52
 **/
@Slf4j
@Component
public class FanoutConsumer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "fanout", type = "fanout")))
    public void receiveOne(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{} 被消费端One消费", message);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "fanout", type = "fanout")))
    public void receiveTwo(String message) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{} 被消费端Two消费", message);
    }
}
