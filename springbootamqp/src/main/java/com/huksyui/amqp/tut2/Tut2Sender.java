package com.huksyui.amqp.tut2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huskyui
 * @date 2020/3/11 18:26
 */

public class Tut2Sender {
    private static Logger logger = LoggerFactory.getLogger(Tut2Sender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Queue queue;

    AtomicInteger dots = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000,initialDelay = 500)
    public void send(){
        StringBuilder builder = new StringBuilder("hello");
        if(dots.getAndIncrement() == 3){
            dots.set(1);
        }
        for(int i = 0;i<dots.get();i++){
            builder.append(".");
        }
        builder.append(count.incrementAndGet());
        String msg = builder.toString();
        rabbitTemplate.convertAndSend(queue.getName(),msg);
        logger.info("[x] sent {}",msg);


    }

}
