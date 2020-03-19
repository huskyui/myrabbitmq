package com.mmr.rabbitmq.work;

import com.mmr.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author huskyui
 * @date 2020/2/19 17:56
 */

public class Send {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = ConnectionUtils.getConnection();
        // 声明队列
        Channel channel = connection.createChannel();
        //
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 1000; i++) {
            String msg = "msg " + i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }

        channel.close();
        connection.close();
    }

}
