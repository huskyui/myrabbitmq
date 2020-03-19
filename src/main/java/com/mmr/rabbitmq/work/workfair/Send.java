package com.mmr.rabbitmq.work.workfair;

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
        // 获取channel
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        /**
         * 每个消费者发送确认消息之前，消息队列不发送下一条消息到消费者，一次只处理一个消息
         * 限制发送给同一个消费者不得超过一条消息
         * channel.basicQos(int prefetchCount)
         * prefetchCount maximum number of messages that the server
         * will deliver, 0 if unlimited  服务器运送信息的最大数目，0是无限制
         * */

        channel.basicQos(1);

        for (int i = 0; i < 50; i++) {
            String msg = "msg " + i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }

        channel.close();
        connection.close();
    }

}
