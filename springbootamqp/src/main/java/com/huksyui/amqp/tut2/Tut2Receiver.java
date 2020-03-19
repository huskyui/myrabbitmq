package com.huksyui.amqp.tut2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author huskyui
 * @date 2020/3/11 18:35
 */
@RabbitListener(queues = "tut.hello")
public class Tut2Receiver {
    private final static Logger logger = LoggerFactory.getLogger(Tut2Receiver.class);

    private final int instance;

    public Tut2Receiver(int i){
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException{
        StopWatch watch = new StopWatch();
        watch.start();
        logger.info("instance " + this.instance+"[x] recived" + in);
        doWork(in);
        watch.stop();
        logger.info("instance " + this.instance +"[x] done");
    }

    private void doWork(String in) throws InterruptedException{
        for (char ch:in.toCharArray()){
            if(ch == '.'){
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

}
