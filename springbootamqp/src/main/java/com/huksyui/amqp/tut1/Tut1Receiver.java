package com.huksyui.amqp.tut1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author huskyui
 * @date 2020/3/11 16:26
 */
@RabbitListener(queues = "hello")
public class Tut1Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Tut1Receiver.class);

    @RabbitHandler
    public void receive(String in) {
        logger.info("[x] Received:{} ", in);
        System.out.println("[x] Received:{} "+ in);
    }
}
