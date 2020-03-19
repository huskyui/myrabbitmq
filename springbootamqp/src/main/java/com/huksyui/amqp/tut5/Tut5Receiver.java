package com.huksyui.amqp.tut5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;
import java.util.concurrent.TimeUnit;

/**
 * @author huskyui
 * @date 2020/3/18 17:30
 */

public class Tut5Receiver {
    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }


    public void receive(String in, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + receiver + " [X] received ' " + in + " '");
        doWork(in);
        watch.stop();
        System.out.println("instance " + receiver + " [X] Done in" + watch.getTotalTimeSeconds() +"s");
    }

    private void doWork(String in) throws InterruptedException{
        for(char ch : in.toCharArray()){
            if(ch == '.'){
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

}
