package com.mmr.rabbitmq.confirm;

import com.mmr.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author huskyui
 * @date 2020/5/6 23:24
 */

public class Send2 {
    private final static String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 生产者调用confirmSelect将channel设置为confirm模式注意
        // 批量
        channel.confirmSelect();
        for (int i = 0;i<10;i++) {
            String message = "hello confirm message " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }
        if(!channel.waitForConfirms()){
            System.out.println("message send failed");
        }else{
            System.out.println("message send ok");
        }
        channel.close();
        connection.close();
    }
}
