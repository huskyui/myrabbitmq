package com.huksyui.amqp.tut1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * @author huskyui
 * @date 2020/3/11 16:30
 */

public class Tut1Sender {
    private final static Logger logger = LoggerFactory.getLogger(Tut1Sender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String msg = "hello world";
        this.rabbitTemplate.convertAndSend(queue.getName(), msg);
        logger.info("[x] sent :" + msg);
        System.out.println("[x] sent :" + msg);
    }

}
