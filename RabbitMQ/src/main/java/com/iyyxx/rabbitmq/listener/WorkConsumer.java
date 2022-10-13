package com.iyyxx.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
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
public class WorkConsumer {

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", queuesToDeclare = @Queue("work"))
    public void receiveOne(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{} 被消费端One消费", message);
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", queuesToDeclare = @Queue("work"))
    public void receiveTwo(String message) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{} 被消费端Two消费", message);
    }
}
