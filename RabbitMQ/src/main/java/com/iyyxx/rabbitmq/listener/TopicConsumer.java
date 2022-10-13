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
public class TopicConsumer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            key = {"lin.zhi.hui"},
            exchange = @Exchange(name = "topic", type = "topic")))
    public void receiveOne(String message) {
        log.info("{} 被消费端One消费", message);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            key = {"lin.zhi.*"},
            exchange = @Exchange(name = "topic", type = "topic")))
    public void receiveTwo(String message) {
        log.info("{} 被消费端Two消费", message);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            key = {"lin.zhi.#"},
            exchange = @Exchange(name = "topic", type = "topic")))
    public void receiveThree(String message) {
        log.info("{} 被消费端Three消费", message);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            key = {"lin.#"},
            exchange = @Exchange(name = "topic", type = "topic")))
    public void receiveFour(String message) {
        log.info("{} 被消费端Four消费", message);
    }
}
