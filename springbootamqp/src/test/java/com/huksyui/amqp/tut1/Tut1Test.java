package com.huksyui.amqp.tut1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huskyui
 * @date 2020/3/11 17:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Tut1Test {
    @Autowired
    private Tut1Sender tut1Sender;
    @Autowired
    private Tut1Receiver tut1Receiver;

    @Test
    public void send(){
        tut1Sender.send();
    }

}
